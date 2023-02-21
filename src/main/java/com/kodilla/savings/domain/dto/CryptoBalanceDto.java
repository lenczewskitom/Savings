package com.kodilla.savings.domain.dto;

import com.kodilla.savings.domain.enums.CryptoCurrency;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CryptoBalanceDto {

    private long cryptoBalanceId;
    private BigDecimal balance;
    private CryptoCurrency cryptocurrencyCode;
}
