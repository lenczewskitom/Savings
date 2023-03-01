package com.kodilla.savings.scheduler;

import com.kodilla.savings.domain.CurrencyOrder;
import com.kodilla.savings.service.CurrencyOrderDbService;
import com.kodilla.savings.service.CurrencyTransactionDbService;
import com.kodilla.savings.service.api.NbpApiDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrencyOrderScheduler {

    private final NbpApiDbService nbpApiDbService;
    private final CurrencyOrderDbService currencyOrderDbService;
    private final CurrencyTransactionDbService currencyTransactionDbService;

    @Scheduled(cron = "0 */5 * * * *")
    public void checkCurrencyOrders() {
        checkBuyCurrencyOrders();
        checkSellCurrencyOrders();
    }

    public void checkBuyCurrencyOrders() {
        if (!currencyOrderDbService.getBuyCurrencyOrders().isEmpty()) {
            for(CurrencyOrder order : currencyOrderDbService.getBuyCurrencyOrders()) {
                if (nbpApiDbService.getRates(order.getCurrencyCode()).getRate().compareTo(order.getCurrencyRate()) == -1) {
                    currencyTransactionDbService.buyCurrency(
                            order.getOrderCurrencyValue().multiply(nbpApiDbService.getRates(order.getCurrencyCode()).getRate()),
                            order.getCurrencyCode(),
                            order.getOrderCurrencyValue()
                    );
                    order.setActiveOrder(false);
                }
            }
        }
    }

    public void checkSellCurrencyOrders() {
        if (!currencyOrderDbService.getSellCurrencyOrders().isEmpty()) {
            for(CurrencyOrder order : currencyOrderDbService.getSellCurrencyOrders()) {
                if (nbpApiDbService.getRates(order.getCurrencyCode()).getRate().compareTo(order.getCurrencyRate()) == 1) {
                    currencyTransactionDbService.buyCurrency(
                            order.getOrderCurrencyValue().multiply(nbpApiDbService.getRates(order.getCurrencyCode()).getRate()),
                            order.getCurrencyCode(),
                            order.getOrderCurrencyValue()
                    );
                    order.setActiveOrder(false);
                }
            }
        }
    }
}
