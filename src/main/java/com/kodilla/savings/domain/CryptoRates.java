package com.kodilla.savings.domain;

import com.kodilla.savings.domain.enums.CryptoCurrency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NamedNativeQuery;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NamedNativeQuery(
        name = "CryptoRates.getLastCryptoRate",
        query = "select * from crypto_rates WHERE cryptocurrency_Code = :CODE " +
                "ORDER BY crypto_Rate_Id DESC LIMIT 1",
        resultClass = CryptoRates.class
)

@NamedNativeQuery(
        name = "CryptoRates.getLastCryptoRatesList",
        query = "SELECT * FROM (SELECT * FROM crypto_rates ORDER BY crypto_Rate_Id DESC LIMIT 5) last " +
                "ORDER BY ABS(rate_Change) DESC LIMIT 3",
        resultClass = CryptoRates.class
)

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CryptoRates")
public class CryptoRates {

    public CryptoRates(BigDecimal lastRate, BigDecimal rateChange, CryptoCurrency cryptocurrencyCode) {
        this.lastRate = lastRate;
        this.rateChange = rateChange;
        this.cryptocurrencyCode = cryptocurrencyCode;
    }

    public CryptoRates(BigDecimal lastRate, CryptoCurrency cryptocurrencyCode) {
        this.lastRate = lastRate;
        this.cryptocurrencyCode = cryptocurrencyCode;
    }

    @Id
    @GeneratedValue
    @NotNull
    private long cryptoRateId;
    private BigDecimal lastRate;
    private BigDecimal rateChange;
    @Enumerated(EnumType.STRING)
    private CryptoCurrency cryptocurrencyCode;
}
