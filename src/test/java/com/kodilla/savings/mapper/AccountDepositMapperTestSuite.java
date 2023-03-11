package com.kodilla.savings.mapper;

import com.kodilla.savings.domain.AccountDeposit;
import com.kodilla.savings.domain.dto.AccountDepositDto;
import com.kodilla.savings.domain.enums.DepositType;
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
public class AccountDepositMapperTestSuite {

    @Autowired
    private AccountDepositMapper accountDepositMapper;
    private AccountDeposit accountDeposit;
    private List<AccountDeposit> accountDeposits = new ArrayList<>();

    @BeforeAll
    public void prepareData() {
        accountDeposit = new AccountDeposit(1L, LocalDate.now(), BigDecimal.valueOf(1000), DepositType.ACCOUNT);
        accountDeposits.add(accountDeposit);
    }

    @Test
    public void mapToAccountDepositDto() {
        //When
        AccountDepositDto expected = accountDepositMapper.mapToAccountDepositDto(accountDeposit);
        //Then
        assertEquals(1L, expected.getDepositId());
        assertEquals(LocalDate.now(), expected.getDepositDate());
        assertEquals(BigDecimal.valueOf(1000), expected.getDepositValue());
        assertEquals(DepositType.ACCOUNT, expected.getDepositType());
    }

    @Test
    public void mapToAccountDepositDtoList() {
        //When
        List<AccountDepositDto> expected = accountDepositMapper.mapToAccountDepositDtoList(accountDeposits);
        //Then
        assertEquals(1, expected.size());
    }
}
