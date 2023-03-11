package com.kodilla.savings.mapper;

import com.kodilla.savings.domain.CryptoOrder;
import com.kodilla.savings.domain.dto.CryptoOrderDto;
import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.domain.enums.Order;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CryptoOrderMapperTestSuite {

    @Autowired
    private CryptoOrderMapper cryptoOrderMapper;
    private CryptoOrder cryptoOrder;
    private List<CryptoOrder> cryptoOrders = new ArrayList<>();

    @BeforeAll
    void prepareData() {
        cryptoOrder = new CryptoOrder(1L, LocalDate.now(), BigDecimal.valueOf(100), CryptoCurrency.SOL,
                BigDecimal.valueOf(90), Order.BUY);
        cryptoOrders.add(cryptoOrder);
    }

    @Test
    public void mapToCryptoOrderDto() {
        //When
        CryptoOrderDto expected = cryptoOrderMapper.mapToCryptoOrderDto(cryptoOrder);
        //Then
        assertEquals(1L, expected.getCryptoOrderId());
        assertEquals(LocalDate.now(), expected.getCryptoOrderDate());
        assertEquals(BigDecimal.valueOf(100), expected.getOrderCryptoValue());
        assertEquals(CryptoCurrency.SOL, expected.getCryptoCode());
        assertEquals(BigDecimal.valueOf(90), expected.getCryptoRate());
    }

    @Test
    public void mapToCryptoOrderDtoList() {
        //When
        List<CryptoOrderDto> expected = cryptoOrderMapper.mapToCryptoOrderDtoList(cryptoOrders);
        //Then
        assertEquals(1, expected.size());
    }
}
