package com.kodilla.savings.service;

import com.kodilla.savings.client.NbpApiClient;
import com.kodilla.savings.domain.*;
import com.kodilla.savings.domain.dto.nbp.RatesDto;
import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.domain.enums.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DbService {

    private List<AccountRequest> accountRequests = new ArrayList<>();
    private List<CurrencyRequest> currencyRequests = new ArrayList<>();
    private List<CryptoCurrencyRequest> cryptoCurrencyRequests = new ArrayList<>();
    private AccountBalance accountBalance = new AccountBalance();
    private CurrencyBalance currencyBalanceUSD = new CurrencyBalance();
    private CurrencyBalance currencyBalanceEUR = new CurrencyBalance();
    private CurrencyBalance currencyBalanceGBP = new CurrencyBalance();
    private CurrencyBalance currencyBalanceCHF = new CurrencyBalance();
    private CurrencyBalance currencyBalanceCNY = new CurrencyBalance();

    private CryptoCurrencyBalance cryptocurrencyBalanceBTC = new CryptoCurrencyBalance();
    private CryptoCurrencyBalance cryptocurrencyBalanceETH = new CryptoCurrencyBalance();
    private CryptoCurrencyBalance cryptocurrencyBalanceLTC = new CryptoCurrencyBalance();
    private CryptoCurrencyBalance cryptocurrencyBalanceSOL = new CryptoCurrencyBalance();
    private CryptoCurrencyBalance cryptocurrencyBalanceDOGE = new CryptoCurrencyBalance();

    private final NbpApiClient nbpApiClient;


    public List<AccountRequest> getAllAccountDeposits() {
        return accountRequests;
    }

    public void addDeposit(BigDecimal value) {
        accountRequests.add(new AccountRequest(1L, value.setScale(2, RoundingMode.CEILING)));
        accountBalance.updateBalance(value);
    }

    public void addPayment(BigDecimal value) {
        value = value.negate();
        accountRequests.add(new AccountRequest(2L, value.setScale(2, RoundingMode.CEILING)));
        accountBalance.updateBalance(value);
    }


    public AccountBalance getAccountBalance() {
        return accountBalance;
    }

    public RatesDto getRates(Currency currency) {

        return nbpApiClient.getExchangeRate(currency);
    }

    public void buyCurrency(BigDecimal accountValue, Currency currencyCode, BigDecimal currencyValue) {
        currencyRequests.add(new CurrencyRequest(1L, accountValue.setScale(2, RoundingMode.CEILING), currencyCode, currencyValue));
        addPayment(accountValue);
        switch (currencyCode) {
            case EUR -> currencyBalanceEUR.updateBalance(currencyValue, currencyCode);
            case USD -> currencyBalanceUSD.updateBalance(currencyValue, currencyCode);
            case GBP -> currencyBalanceGBP.updateBalance(currencyValue, currencyCode);
            case CHF -> currencyBalanceCHF.updateBalance(currencyValue, currencyCode);
            case CNY -> currencyBalanceCNY.updateBalance(currencyValue, currencyCode);
        }
    }

    public void sellCurrency(BigDecimal accountValue, Currency currencyCode, BigDecimal currencyValue) {
        currencyValue = currencyValue.negate();
        currencyRequests.add(new CurrencyRequest(2L, accountValue.setScale(2, RoundingMode.CEILING), currencyCode, currencyValue));
        switch (currencyCode) {
            case EUR -> currencyBalanceEUR.updateBalance(currencyValue, currencyCode);
            case USD -> currencyBalanceUSD.updateBalance(currencyValue, currencyCode);
            case GBP -> currencyBalanceGBP.updateBalance(currencyValue, currencyCode);
            case CHF -> currencyBalanceCHF.updateBalance(currencyValue, currencyCode);
            case CNY -> currencyBalanceCNY.updateBalance(currencyValue, currencyCode);
        }
        addDeposit(accountValue);
    }
    public List<CurrencyRequest> getAllCurrencyTransactions() {
        return currencyRequests;
    }

    public CurrencyBalance getCurrencyBalance(Currency currencyCode) {
        CurrencyBalance currencyBalance = switch (currencyCode) {
            case EUR -> currencyBalanceEUR;
            case USD -> currencyBalanceUSD;
            case GBP -> currencyBalanceGBP;
            case CHF -> currencyBalanceCHF;
            case CNY -> currencyBalanceCNY;
        };
        return currencyBalance;
    }

    public List<CryptoCurrencyRequest> getAllCryptoCurrencyTransactions() {
        return cryptoCurrencyRequests;
    }

    public void getCryptoRates(CryptoCurrency cryptoCurrencyCode) {

    }

    public void buyCryptocurrency(BigDecimal accountValue, CryptoCurrency cryptocurrencyCode, BigDecimal cryptocurrencyValue) {
        cryptoCurrencyRequests.add(new CryptoCurrencyRequest(1L, accountValue.setScale(2, RoundingMode.CEILING), cryptocurrencyCode, cryptocurrencyValue));
        addPayment(accountValue);
        switch (cryptocurrencyCode) {
            case BTC -> cryptocurrencyBalanceBTC.updateBalance(cryptocurrencyValue, cryptocurrencyCode);
            case ETH -> cryptocurrencyBalanceETH.updateBalance(cryptocurrencyValue, cryptocurrencyCode);
            case LTC -> cryptocurrencyBalanceLTC.updateBalance(cryptocurrencyValue, cryptocurrencyCode);
            case SOL -> cryptocurrencyBalanceSOL.updateBalance(cryptocurrencyValue, cryptocurrencyCode);
            case DOGE -> cryptocurrencyBalanceDOGE.updateBalance(cryptocurrencyValue, cryptocurrencyCode);
        }
    }

    public void sellCryptocurrency(BigDecimal accountValue, CryptoCurrency cryptocurrencyCode, BigDecimal cryptocurrencyValue) {
        cryptocurrencyValue = cryptocurrencyValue.negate();
        cryptoCurrencyRequests.add(new CryptoCurrencyRequest(2L, accountValue.setScale(2, RoundingMode.CEILING),cryptocurrencyCode, cryptocurrencyValue));
        switch (cryptocurrencyCode) {
            case BTC -> cryptocurrencyBalanceBTC.updateBalance(cryptocurrencyValue, cryptocurrencyCode);
            case ETH -> cryptocurrencyBalanceETH.updateBalance(cryptocurrencyValue, cryptocurrencyCode);
            case LTC -> cryptocurrencyBalanceLTC.updateBalance(cryptocurrencyValue, cryptocurrencyCode);
            case SOL -> cryptocurrencyBalanceSOL.updateBalance(cryptocurrencyValue, cryptocurrencyCode);
            case DOGE -> cryptocurrencyBalanceDOGE.updateBalance(cryptocurrencyValue, cryptocurrencyCode);
        }
    }

    public CryptoCurrencyBalance getCryptocurrencyBalance(CryptoCurrency cryptocurrencyCode) {
        CryptoCurrencyBalance cryptocurrencyBalance = switch (cryptocurrencyCode) {
            case BTC -> cryptocurrencyBalanceBTC;
            case ETH -> cryptocurrencyBalanceETH;
            case LTC -> cryptocurrencyBalanceLTC;
            case SOL -> cryptocurrencyBalanceSOL;
            case DOGE -> cryptocurrencyBalanceDOGE;
        };
        return cryptocurrencyBalance;
    }
}
