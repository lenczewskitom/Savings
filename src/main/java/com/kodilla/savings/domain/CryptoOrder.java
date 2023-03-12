package com.kodilla.savings.domain;

import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.domain.enums.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NamedQuery;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@NamedQuery(
        name = "CryptoOrder.getBuyCryptoOrders",
        query = "FROM CryptoOrder WHERE operationType = 'BUY'"
)

@NamedQuery(
        name = "CryptoOrder.getSellCryptoOrders",
        query = "FROM CryptoOrder WHERE operationType = 'SELL'"
)

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CryptoOrder")
public class CryptoOrder {

    public CryptoOrder(BigDecimal orderCryptoValue, CryptoCurrency cryptoCode, BigDecimal cryptoRate, Order operationType) {
        this.cryptoOrderDate = LocalDate.now();
        this.orderCryptoValue = orderCryptoValue;
        this.cryptoCode = cryptoCode;
        this.cryptoRate = cryptoRate;
        this.operationType = operationType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long cryptoOrderId;
    private LocalDate cryptoOrderDate;
    private BigDecimal orderCryptoValue;
    @Enumerated(EnumType.STRING)
    private CryptoCurrency cryptoCode;
    private BigDecimal cryptoRate;
    @Enumerated(EnumType.STRING)
    private Order operationType;
}
