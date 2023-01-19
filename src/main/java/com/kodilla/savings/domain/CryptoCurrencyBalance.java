package com.kodilla.savings.domain;

import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.domain.enums.Currency;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CryptoCurrencyBalance {

    private BigDecimal balance = new BigDecimal(0);
    private CryptoCurrency cryptocurrencyCode;

    public void updateBalance(BigDecimal value, CryptoCurrency cryptocurrencyCode) {
        setBalance(balance.add(value));
        setCryptocurrencyCode(cryptocurrencyCode);
    }
}
