package com.kodilla.savings.domain;

import com.kodilla.savings.domain.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@NamedQuery(
        name = "CurrencyTransaction.reverseCurrencyTransactionsList",
        query = "FROM CurrencyTransaction ORDER BY transactionId DESC"
)

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CurrencyTransaction {

    public CurrencyTransaction(BigDecimal transactionAccountValue, Currency currencyCode, BigDecimal transactionCurrencyValue) {
        this.transactionDate = LocalDate.now();
        this.transactionAccountValue = transactionAccountValue;
        this.transactionCurrencyValue = transactionCurrencyValue;
        this.currencyCode = currencyCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long transactionId;
    private LocalDate transactionDate;
    private BigDecimal transactionAccountValue;
    @Enumerated(EnumType.STRING)
    private Currency currencyCode;
    private BigDecimal transactionCurrencyValue;

}
