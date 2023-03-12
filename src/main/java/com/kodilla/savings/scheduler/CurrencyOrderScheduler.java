package com.kodilla.savings.scheduler;

import com.kodilla.savings.config.AdminConfig;
import com.kodilla.savings.domain.CurrencyOrder;
import com.kodilla.savings.domain.Mail;
import com.kodilla.savings.domain.enums.DepositType;
import com.kodilla.savings.exception.NotEnoughCurrencyException;
import com.kodilla.savings.exception.NotEnoughMoneyException;
import com.kodilla.savings.exception.notFound.CurrencyOrderNotFoundException;
import com.kodilla.savings.service.*;
import com.kodilla.savings.service.api.NbpApiDbService;
import com.kodilla.savings.service.mail.SimpleEmailService;
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
    private final AdminConfig adminConfig;
    private final SimpleEmailService simpleEmailService;

    @Scheduled(cron = "0 */5 * * * *")
    public void checkCurrencyOrders() throws CurrencyOrderNotFoundException, NotEnoughMoneyException, NotEnoughCurrencyException {
        checkBuyCurrencyOrders();
        checkSellCurrencyOrders();
    }

    public void checkBuyCurrencyOrders() throws CurrencyOrderNotFoundException, NotEnoughMoneyException {
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
                    accountDepositDbService.withdrawDeposit(accountValue.negate(), DepositType.CURRENCY);
                    currencyOrderDbService.deleteCurrencyOrder(order.getCurrencyOrderId());
                    simpleEmailService.send(
                            new Mail(
                                    adminConfig.getAdminMail(),
                                    "Savings App - Currency Order",
                                    "You bought " + order.getOrderCurrencyValue() + " " + order.getCurrencyCode()
                            )
                    );
                }
            }
        }
    }

    public void checkSellCurrencyOrders() throws CurrencyOrderNotFoundException, NotEnoughCurrencyException {
        if (!currencyOrderDbService.getSellCurrencyOrders().isEmpty()) {
            for(CurrencyOrder order : currencyOrderDbService.getSellCurrencyOrders()) {
                if (nbpApiDbService.getRates(order.getCurrencyCode()).getRate().compareTo(order.getCurrencyRate()) == 1) {
                    BigDecimal accountValue = order.getOrderCurrencyValue().multiply(nbpApiDbService.getRates(order.getCurrencyCode()).getRate());
                    currencyTransactionDbService.sellCurrency(
                            accountValue,
                            order.getCurrencyCode(),
                            order.getOrderCurrencyValue().negate()
                    );
                    currencyBalanceDbService.updateCurrencyBalance(order.getCurrencyCode(), order.getOrderCurrencyValue().negate());
                    accountBalanceDbService.updateAccountBalance(accountValue);
                    accountDepositDbService.addDeposit(accountValue, DepositType.CURRENCY);
                    currencyOrderDbService.deleteCurrencyOrder(order.getCurrencyOrderId());
                    simpleEmailService.send(
                            new Mail(
                                    adminConfig.getAdminMail(),
                                    "Savings App - Currency Order",
                                    "You sold " + order.getOrderCurrencyValue() + " " + order.getCurrencyCode()
                            )
                    );
                }
            }
        }
    }
}
