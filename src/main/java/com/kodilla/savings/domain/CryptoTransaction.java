package com.kodilla.savings.domain;

import com.kodilla.savings.domain.enums.CryptoCurrency;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CryptoTransaction {

    public CryptoTransaction(long transactionId, BigDecimal transactionAccountValue, CryptoCurrency cryptocurrencyCode, BigDecimal transactionCryptocurrencyValue) {
        this.transactionId = transactionId;
        this.transactionDate = LocalDate.now();
        this.transactionAccountValue = transactionAccountValue;
        this.cryptocurrencyCode = cryptocurrencyCode;
        this.transactionCryptocurrencyValue = transactionCryptocurrencyValue;
    }

    private long transactionId;
    private LocalDate transactionDate;
    private BigDecimal transactionAccountValue;
    private CryptoCurrency cryptocurrencyCode;
    private BigDecimal transactionCryptocurrencyValue;
}
