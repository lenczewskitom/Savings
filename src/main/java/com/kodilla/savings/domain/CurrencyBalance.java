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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CurrencyBalance {
    @Id
    @GeneratedValue
    @NotNull
    private long currencyBalanceId;
    private BigDecimal balance = new BigDecimal(0);
    private Currency currencyCode;

    public void updateBalance(BigDecimal value, Currency currencyCode) {
        setBalance(balance.add(value));
        setCurrencyCode(currencyCode);
    }
}
