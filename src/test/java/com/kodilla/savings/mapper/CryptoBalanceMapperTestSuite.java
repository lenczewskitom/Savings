package com.kodilla.savings.mapper;

import com.kodilla.savings.domain.CryptoBalance;
import com.kodilla.savings.domain.dto.CryptoBalanceDto;
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
public class CryptoBalanceMapperTestSuite {

    @Autowired
    private CryptoBalanceMapper cryptoBalanceMapper;
    private CryptoBalance cryptoBalance;
    private List<CryptoBalance> cryptoBalances = new ArrayList<>();

    @BeforeAll
    void prepareData() {
        cryptoBalance = new CryptoBalance(1L, BigDecimal.valueOf(100), CryptoCurrency.SOL);
        cryptoBalances.add(cryptoBalance);
    }

    @Test
    public void mapToCryptoBalanceDto() {
        //When
        CryptoBalanceDto expected = cryptoBalanceMapper.mapToCryptoBalanceDto(cryptoBalance);
        //Then
        assertEquals(1L, expected.getCryptoBalanceId());
        assertEquals(BigDecimal.valueOf(100), expected.getBalance());
        assertEquals(CryptoCurrency.SOL, expected.getCryptocurrencyCode());
    }

    @Test
    public void mapToCryptotBalanceDtoList() {
        //When
        List<CryptoBalanceDto> expected = cryptoBalanceMapper.mapToCryptotBalanceDtoList(cryptoBalances);
        //Then
        assertEquals(1, expected.size());
    }
}
