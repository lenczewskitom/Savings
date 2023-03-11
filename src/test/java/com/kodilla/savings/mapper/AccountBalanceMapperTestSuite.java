package com.kodilla.savings.mapper;

import com.kodilla.savings.domain.AccountBalance;
import com.kodilla.savings.domain.dto.AccountBalanceDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountBalanceMapperTestSuite {

    @Autowired
    private AccountBalanceMapper accountBalanceMapper;
    private AccountBalance accountBalance;

    @BeforeAll
    void prepareData() {
        accountBalance = new AccountBalance(1L, BigDecimal.valueOf(1000));
    }

    @Test
    void mapToAccountBalanceDto() {
        //When
        AccountBalanceDto expected = accountBalanceMapper.mapToAccountBalanceDto(accountBalance);
        //Then
        assertEquals(1L, expected.getAccountBalanceId());
        assertEquals(BigDecimal.valueOf(1000), expected.getBalance());
    }
}
