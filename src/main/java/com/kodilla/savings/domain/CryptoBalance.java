package com.kodilla.savings.domain;

import com.kodilla.savings.domain.enums.CryptoCurrency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@NamedNativeQuery(
        name = "CryptoBalance.getCryptoBalance",
        query = "select * from crypto_balance WHERE cryptocurrency_Code = :CODE" +
                " ORDER BY crypto_Balance_Id DESC LIMIT 1",
        resultClass = CryptoBalance.class
)

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CryptoBalance")
public class CryptoBalance {

    public CryptoBalance(BigDecimal balance, CryptoCurrency cryptocurrencyCode) {
        this.balance = balance;
        this.cryptocurrencyCode = cryptocurrencyCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long cryptoBalanceId;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private CryptoCurrency cryptocurrencyCode;

}
