package com.kodilla.savings.domain.dto;

import com.kodilla.savings.domain.enums.CryptoCurrency;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CryptoTransactionDto {

    private long transactionId;
    private LocalDate transactionDate;
    private BigDecimal transactionAccountValue;
    private CryptoCurrency cryptocurrencyCode;
    private BigDecimal transactionCryptocurrencyValue;
}
