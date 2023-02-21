package com.kodilla.savings.domain.dto;

import com.kodilla.savings.domain.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CurrencyBalanceDto {

    private long currencyBalanceId;
    private BigDecimal balance = new BigDecimal(0);
    private Currency currencyCode;
}
