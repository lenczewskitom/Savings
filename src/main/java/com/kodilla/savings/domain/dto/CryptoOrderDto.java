package com.kodilla.savings.domain.dto;

import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.domain.enums.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CryptoOrderDto {

    private long cryptoOrderId;
    private LocalDate cryptoOrderDate;
    private BigDecimal orderCryptoValue;
    private CryptoCurrency cryptoCode;
    private BigDecimal cryptoRate;
    private Order operationType;
}
