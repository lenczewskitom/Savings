package com.kodilla.savings.scheduler;

import com.kodilla.savings.domain.CurrencyOrder;
import com.kodilla.savings.domain.enums.DepositType;
import com.kodilla.savings.service.*;
import com.kodilla.savings.service.api.NbpApiDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class CurrencyOrderScheduler {

    private final NbpApiDbService nbpApiDbService;
    private final CurrencyOrderDbService currencyOrderDbService;
    private final CurrencyTransactionDbService currencyTransactionDbService;
    private final CurrencyBalanceDbService currencyBalanceDbService;
    private final AccountDepositDbService accountDepositDbService;
    private final AccountBalanceDbService accountBalanceDbService;

    @Scheduled(cron = "0 */1 * * * *")
    public void checkCurrencyOrders() {
        checkBuyCurrencyOrders();
        checkSellCurrencyOrders();
    }

    public void checkBuyCurrencyOrders() {
        if (!currencyOrderDbService.getBuyCurrencyOrders().isEmpty()) {
            for(CurrencyOrder order : currencyOrderDbService.getBuyCurrencyOrders()) {
                if (nbpApiDbService.getRates(order.getCurrencyCode()).getRate().compareTo(order.getCurrencyRate()) == -1) {
                    BigDecimal accountValue = order.getOrderCurrencyValue().multiply(nbpApiDbService.getRates(order.getCurrencyCode()).getRate());
                    currencyTransactionDbService.buyCurrency(
                            accountValue.negate(),
                            order.getCurrencyCode(),
                            order.getOrderCurrencyValue()
                    );
                    currencyBalanceDbService.updateCurrencyBalance(order.getCurrencyCode(), order.getOrderCurrencyValue());
                    accountBalanceDbService.updateAccountBalance(accountValue.negate());
                    accountDepositDbService.addDeposit(accountValue.negate(), DepositType.CURRENCY);
                    currencyOrderDbService.deleteCurrencyOrder(order.getCurrencyOrderId());
                }
            }
        }
    }

    public void checkSellCurrencyOrders() {
        if (!currencyOrderDbService.getSellCurrencyOrders().isEmpty()) {
            for(CurrencyOrder order : currencyOrderDbService.getSellCurrencyOrders()) {
                if (nbpApiDbService.getRates(order.getCurrencyCode()).getRate().compareTo(order.getCurrencyRate()) == 1) {
                    BigDecimal accountValue = order.getOrderCurrencyValue().multiply(nbpApiDbService.getRates(order.getCurrencyCode()).getRate());
                    currencyTransactionDbService.buyCurrency(
                            accountValue,
                            order.getCurrencyCode(),
                            order.getOrderCurrencyValue().negate()
                    );
                    currencyBalanceDbService.updateCurrencyBalance(order.getCurrencyCode(), order.getOrderCurrencyValue().negate());
                    accountBalanceDbService.updateAccountBalance(accountValue);
                    accountDepositDbService.addDeposit(accountValue, DepositType.CURRENCY);
                    currencyOrderDbService.deleteCurrencyOrder(order.getCurrencyOrderId());
                }
            }
        }
    }
}
