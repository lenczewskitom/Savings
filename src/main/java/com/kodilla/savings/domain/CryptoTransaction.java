package com.kodilla.savings.domain;

import com.kodilla.savings.domain.enums.CryptoCurrency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CryptoTransaction {

    public CryptoTransaction(long transactionId, BigDecimal transactionAccountValue, CryptoCurrency cryptocurrencyCode, BigDecimal transactionCryptocurrencyValue) {
        this.transactionId = transactionId;
        this.transactionDate = LocalDate.now();
        this.transactionAccountValue = transactionAccountValue;
        this.cryptocurrencyCode = cryptocurrencyCode;
        this.transactionCryptocurrencyValue = transactionCryptocurrencyValue;
    }

    @Id
    @GeneratedValue
    @NotNull
    private long transactionId;
    private LocalDate transactionDate;
    private BigDecimal transactionAccountValue;
    private CryptoCurrency cryptocurrencyCode;
    private BigDecimal transactionCryptocurrencyValue;
}
