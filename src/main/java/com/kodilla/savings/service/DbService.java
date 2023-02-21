package com.kodilla.savings.service;

import com.kodilla.savings.client.CoinApiClient;
import com.kodilla.savings.client.NbpApiClient;
import com.kodilla.savings.domain.*;
import com.kodilla.savings.domain.dto.nbp.RatesDto;
import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.domain.enums.Currency;
import com.kodilla.savings.domain.coinapi.CoinApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DbService {

    private List<AccountDeposit> accountRequests = new ArrayList<>();
    private List<CurrencyTransaction> currencyTransactions = new ArrayList<>();
    private List<CryptoTransaction> cryptoTransactions = new ArrayList<>();
    private AccountBalance accountBalance = new AccountBalance();
    private CurrencyBalance currencyBalanceUSD = new CurrencyBalance();
    private CurrencyBalance currencyBalanceEUR = new CurrencyBalance();
    private CurrencyBalance currencyBalanceGBP = new CurrencyBalance();
    private CurrencyBalance currencyBalanceCHF = new CurrencyBalance();
    private CurrencyBalance currencyBalanceCNY = new CurrencyBalance();

    private CryptoBalance cryptocurrencyBalanceBTC = new CryptoBalance();
    private CryptoBalance cryptocurrencyBalanceETH = new CryptoBalance();
    private CryptoBalance cryptocurrencyBalanceLTC = new CryptoBalance();
    private CryptoBalance cryptocurrencyBalanceSOL = new CryptoBalance();
    private CryptoBalance cryptocurrencyBalanceDOGE = new CryptoBalance();

    private final NbpApiClient nbpApiClient;
    private final CoinApiClient coinApiResponseDto;


    public List<AccountDeposit> getAllAccountDeposits() {
        return accountRequests;
    }

    public void addDeposit(BigDecimal value) {
        accountRequests.add(new AccountDeposit(1L, value.setScale(2, RoundingMode.CEILING)));
        accountBalance.updateBalance(value);
    }

    public void addPayment(BigDecimal value) {
        value = value.negate();
        accountRequests.add(new AccountDeposit(2L, value.setScale(2, RoundingMode.CEILING)));
        accountBalance.updateBalance(value);
    }


    public AccountBalance getAccountBalance() {
        return accountBalance;
    }

    public RatesDto getRates(Currency currency) {

        return nbpApiClient.getExchangeRate(currency);
    }

    public void buyCurrency(BigDecimal accountValue, Currency currencyCode, BigDecimal currencyValue) {
        currencyTransactions.add(new CurrencyTransaction(1L, accountValue.setScale(2, RoundingMode.CEILING), currencyCode, currencyValue));
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
        currencyTransactions.add(new CurrencyTransaction(2L, accountValue.setScale(2, RoundingMode.CEILING), currencyCode, currencyValue));
        switch (currencyCode) {
            case EUR -> currencyBalanceEUR.updateBalance(currencyValue, currencyCode);
            case USD -> currencyBalanceUSD.updateBalance(currencyValue, currencyCode);
            case GBP -> currencyBalanceGBP.updateBalance(currencyValue, currencyCode);
            case CHF -> currencyBalanceCHF.updateBalance(currencyValue, currencyCode);
            case CNY -> currencyBalanceCNY.updateBalance(currencyValue, currencyCode);
        }
        addDeposit(accountValue);
    }
    public List<CurrencyTransaction> getAllCurrencyTransactions() {
        return currencyTransactions;
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

    public List<CryptoTransaction> getAllCryptoCurrencyTransactions() {
        return cryptoTransactions;
    }

    public CoinApiResponseDto getCryptoRates(CryptoCurrency cryptoCurrencyCode) {
        return coinApiResponseDto.getCryptocurrencyRates(cryptoCurrencyCode);
    }

    public void buyCryptocurrency(BigDecimal accountValue, CryptoCurrency cryptocurrencyCode, BigDecimal cryptocurrencyValue) {
        cryptoTransactions.add(new CryptoTransaction(1L, accountValue.setScale(2, RoundingMode.CEILING), cryptocurrencyCode, cryptocurrencyValue));
        addPayment(accountValue);
        switch (cryptocurrencyCode) {
            case BTC -> cryptocurrencyBalanceBTC.updateBalance(cryptocurrencyValue, cryptocurrencyCode);
            case ETC -> cryptocurrencyBalanceETH.updateBalance(cryptocurrencyValue, cryptocurrencyCode);
            case LTC -> cryptocurrencyBalanceLTC.updateBalance(cryptocurrencyValue, cryptocurrencyCode);
            case SOL -> cryptocurrencyBalanceSOL.updateBalance(cryptocurrencyValue, cryptocurrencyCode);
            case DOGE -> cryptocurrencyBalanceDOGE.updateBalance(cryptocurrencyValue, cryptocurrencyCode);
        }
    }

    public void sellCryptocurrency(BigDecimal accountValue, CryptoCurrency cryptocurrencyCode, BigDecimal cryptocurrencyValue) {
        cryptocurrencyValue = cryptocurrencyValue.negate();
        cryptoTransactions.add(new CryptoTransaction(2L, accountValue.setScale(2, RoundingMode.CEILING),cryptocurrencyCode, cryptocurrencyValue));
        switch (cryptocurrencyCode) {
            case BTC -> cryptocurrencyBalanceBTC.updateBalance(cryptocurrencyValue, cryptocurrencyCode);
            case ETC -> cryptocurrencyBalanceETH.updateBalance(cryptocurrencyValue, cryptocurrencyCode);
            case LTC -> cryptocurrencyBalanceLTC.updateBalance(cryptocurrencyValue, cryptocurrencyCode);
            case SOL -> cryptocurrencyBalanceSOL.updateBalance(cryptocurrencyValue, cryptocurrencyCode);
            case DOGE -> cryptocurrencyBalanceDOGE.updateBalance(cryptocurrencyValue, cryptocurrencyCode);
        }
        addDeposit(accountValue);
    }

    public CryptoBalance getCryptocurrencyBalance(CryptoCurrency cryptocurrencyCode) {
        CryptoBalance cryptocurrencyBalance = switch (cryptocurrencyCode) {
            case BTC -> cryptocurrencyBalanceBTC;
            case ETC -> cryptocurrencyBalanceETH;
            case LTC -> cryptocurrencyBalanceLTC;
            case SOL -> cryptocurrencyBalanceSOL;
            case DOGE -> cryptocurrencyBalanceDOGE;
        };
        return cryptocurrencyBalance;
    }
}
