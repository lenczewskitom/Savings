package com.kodilla.savings.domain;

import com.kodilla.savings.domain.enums.Currency;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrencyBalance {
    private BigDecimal balance = new BigDecimal(0);
    private Currency currencyCode;

    public void updateBalance(BigDecimal value, Currency currencyCode) {
        setBalance(balance.add(value));
        setCurrencyCode(currencyCode);
    }
}
