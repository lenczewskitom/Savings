package com.kodilla.savings.domain;

import com.kodilla.savings.domain.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CurrencyRates {

    @Id
    @GeneratedValue
    @NotNull
    private long currencyRateId;
    private BigDecimal rate;
    private BigDecimal lastRate;
    private BigDecimal rateChange;
    @Enumerated(EnumType.STRING)
    private Currency currencyCode;
}
