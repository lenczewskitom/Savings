package com.kodilla.savings.scheduler;

import com.kodilla.savings.config.AdminConfig;
import com.kodilla.savings.domain.CryptoOrder;
import com.kodilla.savings.domain.Mail;
import com.kodilla.savings.domain.enums.DepositType;
import com.kodilla.savings.exception.NotEnoughCryptoException;
import com.kodilla.savings.exception.NotEnoughMoneyException;
import com.kodilla.savings.exception.TooManyRequestsException;
import com.kodilla.savings.exception.notFound.CryptoOrderNotFoundException;
import com.kodilla.savings.service.*;
import com.kodilla.savings.service.api.CoinApiDbService;
import com.kodilla.savings.service.mail.SimpleEmailService;
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
    private final AdminConfig adminConfig;
    private final SimpleEmailService simpleEmailService;

    @Scheduled(cron = "0 */5 * * * *")
    public void checkCryptoOrders() throws CryptoOrderNotFoundException, NotEnoughMoneyException, NotEnoughCryptoException, TooManyRequestsException {
        checkBuyCryptoOrders();
        checkSellCryptoOrders();
    }

    public void checkBuyCryptoOrders() throws CryptoOrderNotFoundException, NotEnoughMoneyException, TooManyRequestsException {
        if (!cryptoOrderDbService.getBuyCryptoOrders().isEmpty()) {
            for(CryptoOrder order : cryptoOrderDbService.getBuyCryptoOrders()) {
                if (coinApiDbService.getCryptoRates(order.getCryptoCode()).getRate().compareTo(order.getCryptoRate()) == -1) {
                    BigDecimal accountValue = order.getOrderCryptoValue().multiply(coinApiDbService.getCryptoRates(order.getCryptoCode()).getRate());
                    cryptoTransactionDbService.buyCryptocurrency(
                            accountValue.negate(),
                            order.getCryptoCode(),
                            order.getOrderCryptoValue()
                    );
                    cryptoBalanceDbService.addCrypto(order.getCryptoCode(), order.getOrderCryptoValue());
                    accountBalanceDbService.updateAccountBalance(accountValue.negate());
                    accountDepositDbService.withdrawDeposit(accountValue.negate(), DepositType.CRYPTOCURRENCY);
                    cryptoOrderDbService.deleteCryptoOrder(order.getCryptoOrderId());
                    simpleEmailService.send(
                            new Mail(
                                    adminConfig.getAdminMail(),
                                    "Savings App - Currency Order",
                                    "You bought " + order.getOrderCryptoValue() + " " + order.getCryptoCode()
                            )
                    );
                }
            }
        }
    }

    public void checkSellCryptoOrders() throws CryptoOrderNotFoundException, NotEnoughCryptoException, TooManyRequestsException {
        if (!cryptoOrderDbService.getSellCryptoOrders().isEmpty()) {
            for(CryptoOrder order : cryptoOrderDbService.getSellCryptoOrders()) {
                if (coinApiDbService.getCryptoRates(order.getCryptoCode()).getRate().compareTo(order.getCryptoRate()) == 1) {
                    BigDecimal accountValue = order.getOrderCryptoValue().multiply(coinApiDbService.getCryptoRates(order.getCryptoCode()).getRate());
                    cryptoTransactionDbService.sellCryptocurrency(
                            accountValue,
                            order.getCryptoCode(),
                            order.getOrderCryptoValue().negate()
                    );
                    cryptoBalanceDbService.withdrawCrypto(order.getCryptoCode(), order.getOrderCryptoValue().negate());
                    accountBalanceDbService.updateAccountBalance(accountValue);
                    accountDepositDbService.addDeposit(accountValue, DepositType.CRYPTOCURRENCY);
                    cryptoOrderDbService.deleteCryptoOrder(order.getCryptoOrderId());
                    simpleEmailService.send(
                            new Mail(
                                    adminConfig.getAdminMail(),
                                    "Savings App - Currency Order",
                                    "You sold " + order.getOrderCryptoValue() + " " + order.getCryptoCode()
                            )
                    );
                }
            }
        }
    }
}
