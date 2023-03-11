package com.kodilla.savings.mapper;

import com.kodilla.savings.domain.CurrencyTransaction;
import com.kodilla.savings.domain.dto.CurrencyTransactionDto;
import com.kodilla.savings.domain.enums.Currency;
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
public class CurrencyTransactionMapperTestSuite {

    @Autowired
    private CurrencyTransactionMapper currencyTransactionMapper;
    private CurrencyTransaction currencyTransaction;
    private List<CurrencyTransaction> currencyTransactions = new ArrayList<>();

    @BeforeAll
    void prepareData() {
        currencyTransaction = new CurrencyTransaction(1L, LocalDate.now(), BigDecimal.valueOf(1000),
                Currency.EUR, BigDecimal.valueOf(200));
        currencyTransactions.add(currencyTransaction);
    }

    @Test
    public void mapToCurrencyTransactionDto() {
        //When
        CurrencyTransactionDto expected = currencyTransactionMapper.mapToCurrencyTransactionDto(currencyTransaction);
        //Then
        assertEquals(1L, expected.getTransactionId());
        assertEquals(LocalDate.now(), expected.getTransactionDate());
        assertEquals(BigDecimal.valueOf(1000), expected.getTransactionAccountValue());
        assertEquals(Currency.EUR, expected.getCurrencyCode());
        assertEquals(BigDecimal.valueOf(200), expected.getTransactionCurrencyValue());
    }

    @Test
    public void mapToCurrencyTransactionDtoList() {
        //When
        List<CurrencyTransactionDto> expected = currencyTransactionMapper.mapToCurrencyTransactionDtoList(currencyTransactions);
        //Then
        assertEquals(1, expected.size());
    }
}