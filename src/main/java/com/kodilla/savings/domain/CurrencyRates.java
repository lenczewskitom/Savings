package com.kodilla.savings.domain;

import com.kodilla.savings.domain.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.NamedNativeQueries;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "CurrencyRates.getLastCurrencyRate",
                query = "SELECT * FROM currency_rates WHERE currency_Code = :CODE " +
                        "ORDER BY currency_Rate_Id DESC LIMIT 1",
                resultClass = CurrencyRates.class
        ),

        @NamedNativeQuery(
                name = "CurrencyRates.getLastCurrencyRatesList",
                query = "SELECT * FROM (SELECT * FROM currency_rates ORDER BY currency_Rate_Id DESC LIMIT 5) last " +
                        "ORDER BY ABS(rate_Change) DESC LIMIT 3",
                resultClass = CurrencyRates.class
        )
})


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CurrencyRates {

    public CurrencyRates(BigDecimal lastRate, BigDecimal rateChange, Currency currencyCode) {
        this.lastRate = lastRate;
        this.rateChange = rateChange;
        this.currencyCode = currencyCode;
    }

    public CurrencyRates(BigDecimal lastRate, Currency currencyCode) {
        this.lastRate = lastRate;
        this.currencyCode = currencyCode;
    }

    @Id
    @GeneratedValue
    @NotNull
    private long currencyRateId;
    private BigDecimal lastRate;
    private BigDecimal rateChange;
    @Enumerated(EnumType.STRING)
    private Currency currencyCode;
}
