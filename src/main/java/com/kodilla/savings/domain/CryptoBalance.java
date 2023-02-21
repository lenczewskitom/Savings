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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CryptoBalance {

    @Id
    @GeneratedValue
    @NotNull
    private long cryptoBalanceId;
    private BigDecimal balance = new BigDecimal(0);
    private CryptoCurrency cryptocurrencyCode;

    public void updateBalance(BigDecimal value, CryptoCurrency cryptocurrencyCode) {
        setBalance(balance.add(value));
        setCryptocurrencyCode(cryptocurrencyCode);
    }
}
