package com.kodilla.savings.service;

import com.kodilla.savings.domain.CurrencyTransaction;
import com.kodilla.savings.domain.enums.Currency;
import com.kodilla.savings.repository.CurrencyTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyTransactionDbService {

    private final CurrencyTransactionRepository currencyTransactionRepository;

    @Cacheable(cacheNames = "AllCurrencyTransactions")
    public List<CurrencyTransaction> getAllTransactions() {
        return currencyTransactionRepository.reverseCurrencyTransactionsList();
    }

    @CacheEvict(cacheNames = "AllCurrencyTransactions", allEntries = true)
    public void buyCurrency(BigDecimal accountValue, Currency currencyCode,
                            BigDecimal currencyValue) {
        CurrencyTransaction currencyTransaction = new CurrencyTransaction(accountValue, currencyCode, currencyValue);
        currencyTransactionRepository.save(currencyTransaction);
    }

    @CacheEvict(cacheNames = "AllCurrencyTransactions", allEntries = true)
    public void sellCurrency(BigDecimal accountValue, Currency currencyCode,
                            BigDecimal currencyValue) {
        CurrencyTransaction currencyTransaction = new CurrencyTransaction(accountValue, currencyCode, currencyValue);
        currencyTransactionRepository.save(currencyTransaction);
    }
}
