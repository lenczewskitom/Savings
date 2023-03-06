package com.kodilla.savings.domain.dto;

import com.kodilla.savings.domain.enums.Currency;
import com.kodilla.savings.domain.enums.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CurrencyOrderDto {

    private long currencyOrderId;
    private LocalDate currencyOrderDate;
    private BigDecimal orderCurrencyValue;
    private Currency currencyCode;
    private BigDecimal currencyRate;
    private Order operationType;
}
