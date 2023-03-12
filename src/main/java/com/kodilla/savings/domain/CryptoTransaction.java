package com.kodilla.savings.domain;

import com.kodilla.savings.domain.enums.CryptoCurrency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NamedNativeQuery;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;


@NamedQuery(
        name = "CryptoTransaction.reverseCryptoTransactionsList",
        query = "FROM CryptoTransaction ORDER BY transactionId DESC"
)

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CryptoTransaction")
public class CryptoTransaction {

    public CryptoTransaction(BigDecimal transactionAccountValue, CryptoCurrency cryptoCurrencyCode, BigDecimal transactionCryptoValue) {
        this.transactionDate = LocalDate.now();
        this.transactionAccountValue = transactionAccountValue;
        this.cryptoCurrencyCode = cryptoCurrencyCode;
        this.transactionCryptoValue = transactionCryptoValue;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long transactionId;
    private LocalDate transactionDate;
    private BigDecimal transactionAccountValue;
    @Enumerated(EnumType.STRING)
    private CryptoCurrency cryptoCurrencyCode;
    private BigDecimal transactionCryptoValue;
}
