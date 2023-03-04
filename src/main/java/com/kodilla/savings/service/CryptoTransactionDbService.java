package com.kodilla.savings.service;

import com.kodilla.savings.domain.CryptoTransaction;
import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.repository.CryptoTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CryptoTransactionDbService {

    private final CryptoTransactionRepository cryptoTransactionRepository;

    @Cacheable(cacheNames = "AllCryptoTransactions")
    public List<CryptoTransaction> getAllTransactions() {
        return cryptoTransactionRepository.reverseCryptoTransactionsList();
    }

    @CacheEvict(cacheNames = "AllCryptoTransactions", allEntries = true)
    public void buyCryptocurrency(BigDecimal accountValue, CryptoCurrency cryptoCurrencyCode,
                                  BigDecimal cryptocurrencyValue) {
        CryptoTransaction cryptoTransaction = new CryptoTransaction(accountValue, cryptoCurrencyCode, cryptocurrencyValue);
        cryptoTransactionRepository.save(cryptoTransaction);
    }

    @CacheEvict(cacheNames = "AllCryptoTransactions", allEntries = true)
    public void sellCryptocurrency(BigDecimal accountValue, CryptoCurrency cryptoCurrencyCode,
                                  BigDecimal cryptocurrencyValue) {
        CryptoTransaction cryptoTransaction = new CryptoTransaction(accountValue, cryptoCurrencyCode, cryptocurrencyValue);
        cryptoTransactionRepository.save(cryptoTransaction);
    }
}
