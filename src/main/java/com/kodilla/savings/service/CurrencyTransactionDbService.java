package com.kodilla.savings.service;

import com.kodilla.savings.domain.CurrencyTransaction;
import com.kodilla.savings.domain.enums.Currency;
import com.kodilla.savings.repository.CurrencyTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyTransactionDbService {

    private final CurrencyTransactionRepository currencyTransactionRepository;

    public List<CurrencyTransaction> getAllTransactions() {
        return currencyTransactionRepository.reverseCurrencyTransactionsList();
    }

    public void buyCurrency(BigDecimal accountValue, Currency currencyCode,
                            BigDecimal currencyValue) {
        CurrencyTransaction currencyTransaction = new CurrencyTransaction(accountValue, currencyCode, currencyValue);
        currencyTransactionRepository.save(currencyTransaction);
    }

    public void sellCurrency(BigDecimal accountValue, Currency currencyCode,
                            BigDecimal currencyValue) {
        CurrencyTransaction currencyTransaction = new CurrencyTransaction(accountValue, currencyCode, currencyValue);
        currencyTransactionRepository.save(currencyTransaction);
    }
}
