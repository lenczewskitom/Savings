package com.kodilla.savings.domain.dto;

import com.kodilla.savings.domain.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CurrencyRatesDto {

    private long currencyRateId;
    private BigDecimal rate;
    private BigDecimal lastRate;
    private BigDecimal rateChange;
    private Currency currencyCode;
}
