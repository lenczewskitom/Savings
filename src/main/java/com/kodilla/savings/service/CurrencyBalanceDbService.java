package com.kodilla.savings.service;

import com.kodilla.savings.domain.CurrencyBalance;
import com.kodilla.savings.domain.enums.Currency;
import com.kodilla.savings.repository.CurrencyBalanceRepository;
import com.kodilla.savings.service.api.NbpApiDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyBalanceDbService {

    private final CurrencyBalanceRepository currencyBalanceRepository;
    private final NbpApiDbService nbpApiDbService;

    public CurrencyBalance getCurrencyBalance(Currency currency) {
        return currencyBalanceRepository.getCurrencyBalance(currency.name());
    }

    public List<CurrencyBalance> getAllCurrencyBalanceList() {
        List<CurrencyBalance> currencyBalanceList = new ArrayList<>();
        currencyBalanceList.add(currencyBalanceRepository.getCurrencyBalance(Currency.EUR.name()));
        currencyBalanceList.add(currencyBalanceRepository.getCurrencyBalance(Currency.USD.name()));
        currencyBalanceList.add(currencyBalanceRepository.getCurrencyBalance(Currency.GBP.name()));
        currencyBalanceList.add(currencyBalanceRepository.getCurrencyBalance(Currency.CHF.name()));
        currencyBalanceList.add(currencyBalanceRepository.getCurrencyBalance(Currency.CNY.name()));
        return currencyBalanceList;
    }

    public void updateCurrencyBalance(Currency currency, BigDecimal currencyValue) {
        BigDecimal newBalance = currencyBalanceRepository.getCurrencyBalance(currency.name()).getBalance().add(currencyValue);
        CurrencyBalance currencyBalance = new CurrencyBalance(newBalance, currency);
        currencyBalanceRepository.save(currencyBalance);
    }

    public BigDecimal getAllSavings() {
        BigDecimal eur = currencyBalanceRepository.getCurrencyBalance(Currency.EUR.name()).getBalance()
                .multiply(nbpApiDbService.getRates(Currency.EUR).getRate());
        BigDecimal usd = currencyBalanceRepository.getCurrencyBalance(Currency.USD.name()).getBalance()
                .multiply(nbpApiDbService.getRates(Currency.USD).getRate());
        BigDecimal gbp = currencyBalanceRepository.getCurrencyBalance(Currency.GBP.name()).getBalance()
                .multiply(nbpApiDbService.getRates(Currency.GBP).getRate());
        BigDecimal chf = currencyBalanceRepository.getCurrencyBalance(Currency.CHF.name()).getBalance()
                .multiply(nbpApiDbService.getRates(Currency.CHF).getRate());
        BigDecimal cny = currencyBalanceRepository.getCurrencyBalance(Currency.CNY.name()).getBalance()
                .multiply(nbpApiDbService.getRates(Currency.CNY).getRate());
        return eur.add(usd).add(gbp).add(chf).add(cny);
    }
}
