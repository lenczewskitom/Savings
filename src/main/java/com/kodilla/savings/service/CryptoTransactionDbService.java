package com.kodilla.savings.service;

import com.kodilla.savings.domain.CryptoTransaction;
import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.repository.CryptoTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CryptoTransactionDbService {

    private final CryptoTransactionRepository cryptoTransactionRepository;

    public List<CryptoTransaction> getAllTransactions() {
        return cryptoTransactionRepository.reverseCryptoTransactionsList();
    }

    public void buyCryptocurrency(BigDecimal accountValue, CryptoCurrency cryptoCurrencyCode,
                                  BigDecimal cryptocurrencyValue) {
        CryptoTransaction cryptoTransaction = new CryptoTransaction(accountValue, cryptoCurrencyCode, cryptocurrencyValue);
        cryptoTransactionRepository.save(cryptoTransaction);
    }
}
