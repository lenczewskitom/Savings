package com.kodilla.savings.service;

import com.kodilla.savings.domain.CryptoTransaction;
import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.exception.NotEnoughCryptoException;
import com.kodilla.savings.exception.NotEnoughMoneyException;
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
    private final AccountBalanceDbService accountBalanceDbService;
    private final CryptoBalanceDbService cryptoBalanceDbService;

    @Cacheable(cacheNames = "AllCryptoTransactions")
    public List<CryptoTransaction> getAllTransactions() {
        return cryptoTransactionRepository.reverseCryptoTransactionsList();
    }

    @CacheEvict(cacheNames = "AllCryptoTransactions", allEntries = true)
    public void buyCryptocurrency(BigDecimal accountValue, CryptoCurrency cryptoCurrencyCode,
                                  BigDecimal cryptocurrencyValue) throws NotEnoughMoneyException {
        if (accountValue.compareTo(accountBalanceDbService.getAccountBalance().getBalance()) > 0) {
            throw new NotEnoughMoneyException();
        } else {
            CryptoTransaction cryptoTransaction = new CryptoTransaction(accountValue, cryptoCurrencyCode, cryptocurrencyValue);
            cryptoTransactionRepository.save(cryptoTransaction);
        }
    }

    @CacheEvict(cacheNames = "AllCryptoTransactions", allEntries = true)
    public void sellCryptocurrency(BigDecimal accountValue, CryptoCurrency cryptoCurrencyCode,
                                  BigDecimal cryptocurrencyValue) throws NotEnoughCryptoException {
        if (cryptocurrencyValue.compareTo(cryptoBalanceDbService.getCryptoBalance(cryptoCurrencyCode).getBalance()) > 0) {
            throw new NotEnoughCryptoException();
        } else {
            CryptoTransaction cryptoTransaction = new CryptoTransaction(accountValue, cryptoCurrencyCode, cryptocurrencyValue);
            cryptoTransactionRepository.save(cryptoTransaction);
        }
    }
}
