package com.kodilla.savings.service;

import com.kodilla.savings.domain.CurrencyTransaction;
import com.kodilla.savings.domain.enums.Currency;
import com.kodilla.savings.exception.NotEnoughCurrencyException;
import com.kodilla.savings.exception.NotEnoughMoneyException;
import com.kodilla.savings.repository.CurrencyTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyTransactionDbService {

    private final CurrencyTransactionRepository currencyTransactionRepository;
    private final AccountBalanceDbService accountBalanceDbService;
    private final CurrencyBalanceDbService currencyBalanceDbService;

    @Cacheable(cacheNames = "AllCurrencyTransactions")
    public List<CurrencyTransaction> getAllTransactions() {
        return currencyTransactionRepository.reverseCurrencyTransactionsList();
    }

    @CacheEvict(cacheNames = "AllCurrencyTransactions", allEntries = true)
    public void buyCurrency(BigDecimal accountValue, Currency currencyCode,
                            BigDecimal currencyValue) throws NotEnoughMoneyException {
        if (accountValue.compareTo(accountBalanceDbService.getAccountBalance().getBalance()) > 0) {
            throw new NotEnoughMoneyException();
        } else {
            CurrencyTransaction currencyTransaction = new CurrencyTransaction(accountValue, currencyCode, currencyValue);
            currencyTransactionRepository.save(currencyTransaction);
        }
    }

    @CacheEvict(cacheNames = "AllCurrencyTransactions", allEntries = true)
    public void sellCurrency(BigDecimal accountValue, Currency currencyCode,
                            BigDecimal currencyValue) throws NotEnoughCurrencyException {
        if (currencyValue.compareTo(currencyBalanceDbService.getCurrencyBalance(currencyCode).getBalance()) > 0) {
            throw new NotEnoughCurrencyException();
        } else {
            CurrencyTransaction currencyTransaction = new CurrencyTransaction(accountValue, currencyCode, currencyValue);
            currencyTransactionRepository.save(currencyTransaction);
        }
    }
}
