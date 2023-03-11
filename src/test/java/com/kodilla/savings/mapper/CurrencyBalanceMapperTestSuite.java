package com.kodilla.savings.mapper;

import com.kodilla.savings.domain.CurrencyBalance;
import com.kodilla.savings.domain.dto.CurrencyBalanceDto;
import com.kodilla.savings.domain.enums.Currency;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CurrencyBalanceMapperTestSuite {

    @Autowired
    private CurrencyBalanceMapper currencyBalanceMapper;
    private CurrencyBalance currencyBalance;
    private List<CurrencyBalance> currencyBalances = new ArrayList<>();

    @BeforeAll
    void prepareData() {
        currencyBalance = new CurrencyBalance(1L, BigDecimal.valueOf(100), Currency.EUR);
        currencyBalances.add(currencyBalance);
    }

    @Test
    public void mapToCurrencyBalanceDto() {
        //When
        CurrencyBalanceDto expected = currencyBalanceMapper.mapToCurrencyBalanceDto(currencyBalance);
        //Then
        assertEquals(1L, expected.getCurrencyBalanceId());
        assertEquals(BigDecimal.valueOf(100), expected.getBalance());
        assertEquals(Currency.EUR, expected.getCurrencyCode());
    }

    @Test
    public void mapToCurrencyBalanceDtoList() {
        //When
        List<CurrencyBalanceDto> expected = currencyBalanceMapper.mapToCurrencyBalanceDtoList(currencyBalances);
        //Then
        assertEquals(1, expected.size());
    }
}
