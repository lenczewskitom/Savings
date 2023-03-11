package com.kodilla.savings.mapper;

import com.kodilla.savings.domain.CryptoTransaction;
import com.kodilla.savings.domain.dto.CryptoTransactionDto;
import com.kodilla.savings.domain.enums.CryptoCurrency;
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
public class CryptoTransactionMapperTestSuite {

    @Autowired
    private CryptoTransactionMapper cryptoTransactionMapper;
    private CryptoTransaction cryptoTransaction;
    private List<CryptoTransaction> cryptoTransactions = new ArrayList<>();

    @BeforeAll
    void prepareData() {
        cryptoTransaction = new CryptoTransaction(1L, LocalDate.now(), BigDecimal.valueOf(1000),
                CryptoCurrency.SOL, BigDecimal.valueOf(200));
        cryptoTransactions.add(cryptoTransaction);
    }

    @Test
    public void mapToCryptoTransactionDto() {
        //When
        CryptoTransactionDto expected = cryptoTransactionMapper.mapToCryptoTransactionDto(cryptoTransaction);
        //Then
        assertEquals(1L, expected.getTransactionId());
        assertEquals(LocalDate.now(), expected.getTransactionDate());
        assertEquals(BigDecimal.valueOf(1000), expected.getTransactionAccountValue());
        assertEquals(CryptoCurrency.SOL, expected.getCryptoCurrencyCode());
        assertEquals(BigDecimal.valueOf(200), expected.getTransactionCryptoValue());
    }

    @Test
    public void mapToCryptoTransactionDtoList() {
        //When
        List<CryptoTransactionDto> expected = cryptoTransactionMapper.mapToCryptoTransactionDtoList(cryptoTransactions);
        //Then
        assertEquals(1, expected.size());
    }
}
