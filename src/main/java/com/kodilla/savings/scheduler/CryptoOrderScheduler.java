package com.kodilla.savings.scheduler;

import com.kodilla.savings.domain.CryptoOrder;
import com.kodilla.savings.service.*;
import com.kodilla.savings.service.api.CoinApiDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class CryptoOrderScheduler {

    private final CoinApiDbService coinApiDbService;
    private final CryptoOrderDbService cryptoOrderDbService;
    private final CryptoTransactionDbService cryptoTransactionDbService;
    private final CryptoBalanceDbService cryptoBalanceDbService;
    private final AccountDepositDbService accountDepositDbService;
    private final AccountBalanceDbService accountBalanceDbService;

    @Scheduled(cron = "0 0 */3 * * *")
    public void checkCryptoOrders() {
        checkBuyCryptoOrders();
        checkSellCryptoOrders();
    }

    public void checkBuyCryptoOrders() {
        if (!cryptoOrderDbService.getBuyCryptoOrders().isEmpty()) {
            for(CryptoOrder order : cryptoOrderDbService.getBuyCryptoOrders()) {
                if (coinApiDbService.getCryptoRates(order.getCryptoCode()).getRate().compareTo(order.getCryptoRate()) == -1) {
                    BigDecimal accountValue = order.getOrderCryptoValue().multiply(coinApiDbService.getCryptoRates(order.getCryptoCode()).getRate());
                    cryptoTransactionDbService.buyCryptocurrency(
                            accountValue.negate(),
                            order.getCryptoCode(),
                            order.getOrderCryptoValue()
                    );
                    cryptoBalanceDbService.updateCryptoBalance(order.getCryptoCode(), order.getOrderCryptoValue());
                    accountBalanceDbService.updateAccountBalance(accountValue.negate());
                    accountDepositDbService.addDeposit(accountValue.negate());
                    order.setActiveOrder(false);
                }
            }
        }
    }

    public void checkSellCryptoOrders() {
        if (!cryptoOrderDbService.getSellCryptoOrders().isEmpty()) {
            for(CryptoOrder order : cryptoOrderDbService.getSellCryptoOrders()) {
                if (coinApiDbService.getCryptoRates(order.getCryptoCode()).getRate().compareTo(order.getCryptoRate()) == 1) {
                    BigDecimal accountValue = order.getOrderCryptoValue().multiply(coinApiDbService.getCryptoRates(order.getCryptoCode()).getRate());
                    cryptoTransactionDbService.sellCryptocurrency(
                            accountValue,
                            order.getCryptoCode(),
                            order.getOrderCryptoValue().negate()
                    );
                    cryptoBalanceDbService.updateCryptoBalance(order.getCryptoCode(), order.getOrderCryptoValue().negate());
                    accountBalanceDbService.updateAccountBalance(accountValue);
                    accountDepositDbService.addDeposit(accountValue);
                    order.setActiveOrder(false);
                }
            }
        }
    }
}
