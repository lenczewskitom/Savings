package com.kodilla.savings.domain;

import com.kodilla.savings.domain.enums.Currency;
import com.kodilla.savings.domain.enums.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.hibernate.annotations.NamedQuery;


@NamedQuery(
        name = "CurrencyOrder.getBuyCurrencyOrders",
        query = "FROM CurrencyOrder WHERE operationType = 'BUY'"
)

@NamedQuery(
        name = "CurrencyOrder.getSellCurrencyOrders",
        query = "FROM CurrencyOrder WHERE operationType = 'SELL'"
)


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CurrencyOrder")
public class CurrencyOrder {

    public CurrencyOrder(BigDecimal orderCurrencyValue, Currency currencyCode, BigDecimal currencyRate, Order operationType) {
        this.currencyOrderDate = LocalDate.now();
        this.orderCurrencyValue = orderCurrencyValue;
        this.currencyCode = currencyCode;
        this.currencyRate = currencyRate;
        this.operationType = operationType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long currencyOrderId;
    private LocalDate currencyOrderDate;
    private BigDecimal orderCurrencyValue;
    @Enumerated(EnumType.STRING)
    private Currency currencyCode;
    private BigDecimal currencyRate;
    @Enumerated(EnumType.STRING)
    private Order operationType;
}
