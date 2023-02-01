package com.kodilla.savings.domain;

import com.kodilla.savings.domain.enums.Currency;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CurrencyTransaction {

    public CurrencyTransaction(long transactionId, BigDecimal transactionAccountValue, Currency currencyCode, BigDecimal transactionCurrencyValue) {
        this.transactionId = transactionId;
        this.transactionDate = LocalDate.now();
        this.transactionAccountValue = transactionAccountValue;
        this.transactionCurrencyValue = transactionCurrencyValue;
        this.currencyCode = currencyCode;
    }

    private long transactionId;
    private LocalDate transactionDate;
    private BigDecimal transactionAccountValue;
    private Currency currencyCode;
    private BigDecimal transactionCurrencyValue;

}
