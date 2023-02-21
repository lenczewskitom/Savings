package com.kodilla.savings.domain.dto;

import com.kodilla.savings.domain.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CurrencyTransactionDto {

    private long transactionId;
    private LocalDate transactionDate;
    private BigDecimal transactionAccountValue;
    private Currency currencyCode;
    private BigDecimal transactionCurrencyValue;
}
