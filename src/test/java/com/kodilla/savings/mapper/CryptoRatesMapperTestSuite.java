package com.kodilla.savings.mapper;

import com.kodilla.savings.domain.CryptoRates;
import com.kodilla.savings.domain.dto.CryptoRatesDto;
import com.kodilla.savings.domain.enums.CryptoCurrency;
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
public class CryptoRatesMapperTestSuite {

    @Autowired
    private CryptoRatesMapper cryptoRatesMapper;
    private CryptoRates cryptoRates;
    private List<CryptoRates> cryptoRatesList = new ArrayList<>();

    @BeforeAll
    void prepareData() {
        cryptoRates = new CryptoRates(1L, BigDecimal.valueOf(90), BigDecimal.valueOf(10), CryptoCurrency.SOL);
        cryptoRatesList.add(cryptoRates);
    }

    @Test
    public void mapToCryptoBalanceDto() {
        //When
        CryptoRatesDto expected = cryptoRatesMapper.mapToCryptoRatesDto(cryptoRates);
        //Then
        assertEquals(1L, expected.getCryptoRateId());
        assertEquals(BigDecimal.valueOf(90), expected.getLastRate());
        assertEquals(BigDecimal.valueOf(10), expected.getRateChange());
        assertEquals(CryptoCurrency.SOL, expected.getCryptocurrencyCode());
    }

    @Test
    public void mapToCryptotRatesDtoList() {
        //When
        List<CryptoRatesDto> expected = cryptoRatesMapper.mapToCryptotRatesDtoList(cryptoRatesList);
        //Then
        assertEquals(1, expected.size());
    }
}
