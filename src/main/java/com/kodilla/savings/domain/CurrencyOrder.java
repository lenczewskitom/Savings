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
        query = "FROM CurrencyOrder WHERE isActiveOrder = true AND operationType = 'BUY'"
)

@NamedQuery(
        name = "CurrencyOrder.getSellCurrencyOrders",
        query = "FROM CurrencyOrder WHERE isActiveOrder = true AND operationType = 'SELL'"
)


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CurrencyOrder {

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
    private boolean isActiveOrder;
}
