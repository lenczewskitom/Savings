package com.kodilla.savings.domain;

import com.kodilla.savings.domain.enums.Currency;
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
public class CurrencyTransaction {

    public CurrencyTransaction(long transactionId, BigDecimal transactionAccountValue, Currency currencyCode, BigDecimal transactionCurrencyValue) {
        this.transactionId = transactionId;
        this.transactionDate = LocalDate.now();
        this.transactionAccountValue = transactionAccountValue;
        this.transactionCurrencyValue = transactionCurrencyValue;
        this.currencyCode = currencyCode;
    }

    @Id
    @GeneratedValue
    @NotNull
    private long transactionId;
    private LocalDate transactionDate;
    private BigDecimal transactionAccountValue;
    private Currency currencyCode;
    private BigDecimal transactionCurrencyValue;

}
