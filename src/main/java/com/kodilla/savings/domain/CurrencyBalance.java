package com.kodilla.savings.domain;

import com.kodilla.savings.domain.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NamedNativeQuery;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NamedNativeQuery(
        name = "CurrencyBalance.getCurrencyBalance",
        query = "select * from currency_balance WHERE currency_Code = :CODE" +
                " ORDER BY currency_Balance_Id DESC LIMIT 1",
        resultClass = CurrencyBalance.class
)

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CurrencyBalance {

    public CurrencyBalance(BigDecimal balance, Currency currencyCode) {
        this.balance = balance;
        this.currencyCode = currencyCode;
    }

    @Id
    @GeneratedValue
    @NotNull
    private long currencyBalanceId;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private Currency currencyCode;
}
