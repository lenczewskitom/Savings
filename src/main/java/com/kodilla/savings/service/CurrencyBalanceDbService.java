package com.kodilla.savings.service;

import com.kodilla.savings.domain.CryptoBalance;
import com.kodilla.savings.domain.CurrencyBalance;
import com.kodilla.savings.domain.enums.Currency;
import com.kodilla.savings.repository.CurrencyBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CurrencyBalanceDbService {

    private final CurrencyBalanceRepository currencyBalanceRepository;
    public void addData() {
        CurrencyBalance eur = new CurrencyBalance(BigDecimal.ZERO, Currency.EUR);
        CurrencyBalance usd = new CurrencyBalance(BigDecimal.ZERO, Currency.USD);
        CurrencyBalance gbp = new CurrencyBalance(BigDecimal.ZERO, Currency.GBP);
        CurrencyBalance chf = new CurrencyBalance(BigDecimal.ZERO, Currency.CHF);
        CurrencyBalance cny = new CurrencyBalance(BigDecimal.ZERO, Currency.CNY);
        currencyBalanceRepository.save(eur);
        currencyBalanceRepository.save(usd);
        currencyBalanceRepository.save(gbp);
        currencyBalanceRepository.save(chf);
        currencyBalanceRepository.save(cny);
    }

    public CurrencyBalance getCurrencyBalance(Currency currency) {
        return currencyBalanceRepository.getCurrencyBalance(currency.name());
    }

    public void updateCurrencyBalance(Currency currency, BigDecimal currencyValue) {
        BigDecimal newBalance = currencyBalanceRepository.getCurrencyBalance(currency.name()).getBalance().add(currencyValue);
        CurrencyBalance currencyBalance = new CurrencyBalance(newBalance, currency);
        currencyBalanceRepository.save(currencyBalance);
    }
}
