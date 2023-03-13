package com.kodilla.savings.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodilla.savings.domain.AccountBalance;
import com.kodilla.savings.domain.AccountDeposit;
import com.kodilla.savings.domain.dto.AccountBalanceDto;
import com.kodilla.savings.domain.dto.AccountDepositDto;
import com.kodilla.savings.domain.enums.DepositType;
import com.kodilla.savings.service.AccountBalanceDbService;
import com.kodilla.savings.service.AccountDepositDbService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AccountBalanceDbService accountBalanceDbService;
    @MockBean
    private AccountDepositDbService accountDepositDbService;

    private AccountBalance accountBalance;
    private AccountDeposit accountDeposit;
    private List<AccountDeposit> accountDeposits = new ArrayList<>();

    @BeforeAll
    void setUp() {
        accountBalance = new AccountBalance(1L, BigDecimal.valueOf(1000));
        accountDeposit = new AccountDeposit(1L, LocalDate.now(), BigDecimal.valueOf(1000), DepositType.ACCOUNT);
        accountDeposits.add(accountDeposit);
    }

    @Test
    void shouldGetAccountBalance() throws Exception {
        //given
        when(accountBalanceDbService.getAccountBalance()).thenReturn(accountBalance);
        //when
        MvcResult mvcResult = mockMvc.perform(get("/v1/account/balance"))
                .andExpect(status().is(200))
                .andReturn();
        //then
        AccountBalanceDto accountBalanceDto = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), AccountBalanceDto.class);
        assertThat(accountBalanceDto).isNotNull();
        assertEquals(1L, accountBalanceDto.getAccountBalanceId());
        assertEquals(BigDecimal.valueOf(1000), accountBalanceDto.getBalance());
    }

    @Test
    void shouldGetAccountDeposits() throws Exception {
        //given
        when(accountDepositDbService.getAccountDeposits()).thenReturn(accountDeposits);
        //when
        MvcResult mvcResult = mockMvc.perform(get("/v1/account/deposits"))
                .andExpect(status().is(200))
                .andReturn();
        //then
        List<AccountDepositDto> accountDepositDtos = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ArrayList.class);
        assertThat(accountDepositDtos).isNotNull();
        assertEquals(accountDepositDtos.size(), 1);
    }

    @Test
    void shouldAddDeposit() throws Exception {
        //given
        //when&then
        mockMvc.perform(post("/v1/account/add?deposit=1000"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldWithdrawDeposit() throws Exception {
        //given
        //when&then
        mockMvc.perform(post("/v1/account/withdraw?deposit=1000"))
                .andExpect(status().isOk());
    }
}
