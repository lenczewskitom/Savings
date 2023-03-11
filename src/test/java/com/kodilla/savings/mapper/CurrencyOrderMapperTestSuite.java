package com.kodilla.savings.mapper;

import com.kodilla.savings.domain.CurrencyOrder;
import com.kodilla.savings.domain.dto.CurrencyOrderDto;
import com.kodilla.savings.domain.enums.Currency;
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
public class CurrencyOrderMapperTestSuite {

    @Autowired
    private CurrencyOrderMapper currencyOrderMapper;
    private CurrencyOrder currencyOrder;
    private List<CurrencyOrder> currencyOrders = new ArrayList<>();

    @BeforeAll
    void prepareData() {
        currencyOrder = new CurrencyOrder(1L, LocalDate.now(), BigDecimal.valueOf(100), Currency.EUR,
                BigDecimal.valueOf(4.5), Order.BUY);
        currencyOrders.add(currencyOrder);
    }

    @Test
    public void mapToCurrencyOrderDto() {
        //When
        CurrencyOrderDto expected = currencyOrderMapper.mapToCurrencyOrderDto(currencyOrder);
        //Then
        assertEquals(1L, expected.getCurrencyOrderId());
        assertEquals(LocalDate.now(), expected.getCurrencyOrderDate());
        assertEquals(BigDecimal.valueOf(100), expected.getOrderCurrencyValue());
        assertEquals(Currency.EUR, expected.getCurrencyCode());
        assertEquals(BigDecimal.valueOf(4.5), expected.getCurrencyRate());
        assertEquals(Order.BUY, expected.getOperationType());
    }

    @Test
    public void mapToCurrencyOrderDtoList() {
        //When
        List<CurrencyOrderDto> expected = currencyOrderMapper.mapToCurrencyOrderDtoList(currencyOrders);
        //Then
        assertEquals(1, expected.size());
    }
}