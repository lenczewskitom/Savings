package com.kodilla.savings.mapper;

import com.kodilla.savings.domain.CurrencyRates;
import com.kodilla.savings.domain.dto.CurrencyRatesDto;
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
public class CurrencyRatesMapperTestSuite {

    @Autowired
    private CurrencyRatesMapper currencyRatesMapper;
    private CurrencyRates currencyRates;
    private List<CurrencyRates> currencyRatesList = new ArrayList<>();

    @BeforeAll
    void prepareData() {
        currencyRates = new CurrencyRates(1L, BigDecimal.valueOf(4.5), BigDecimal.valueOf(20), Currency.EUR);
        currencyRatesList.add(currencyRates);
    }

    @Test
    public void mapToCurrencyRatesDto() {
        //When
        CurrencyRatesDto expected = currencyRatesMapper.mapToCurrencyRatesDto(currencyRates);
        //Then
        assertEquals(1L, expected.getCurrencyRateId());
        assertEquals(BigDecimal.valueOf(4.5), expected.getLastRate());
        assertEquals(BigDecimal.valueOf(20), expected.getRateChange());
        assertEquals(Currency.EUR, expected.getCurrencyCode());
    }

    @Test
    public void mapToCurrencyRatesDtoList() {
        //When
        List<CurrencyRatesDto> expected = currencyRatesMapper.mapToCurrencyRatesDtoList(currencyRatesList);
        //Then
        assertEquals(1, expected.size());
    }
}