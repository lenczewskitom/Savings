package com.kodilla.savings.domain.dto;

import com.kodilla.savings.domain.enums.CryptoCurrency;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CryptoRatesDto {

    private long cryptoRateId;
    private BigDecimal lastRate;
    private BigDecimal rateChange;
    private CryptoCurrency cryptocurrencyCode;
}
