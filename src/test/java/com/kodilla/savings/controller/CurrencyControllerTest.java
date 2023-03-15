package com.kodilla.savings.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodilla.savings.domain.AccountBalance;
import com.kodilla.savings.domain.CurrencyBalance;
import com.kodilla.savings.domain.CurrencyTransaction;
import com.kodilla.savings.domain.dto.CurrencyBalanceDto;
import com.kodilla.savings.domain.dto.CurrencyTransactionDto;
import com.kodilla.savings.domain.enums.Currency;
import com.kodilla.savings.service.AccountBalanceDbService;
import com.kodilla.savings.service.CurrencyBalanceDbService;
import com.kodilla.savings.service.CurrencyTransactionDbService;
import com.kodilla.savings.service.api.NbpApiDbService;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CurrencyBalanceDbService currencyBalanceDbService;
    @MockBean
    private CurrencyTransactionDbService currencyTransactionDbService;
    @MockBean
    private AccountBalanceDbService accountBalanceDbService;
    @MockBean
    private NbpApiDbService nbpApiDbService;
    private CurrencyBalance currencyBalance;
    private CurrencyTransaction currencyTransaction;
    private AccountBalance accountBalance;

    private List<CurrencyBalance> currencyBalances = new ArrayList<>();
    private List<CurrencyTransaction> currencyTransactions = new ArrayList<>();
    @BeforeAll
    void setUp() {
        currencyBalance = new CurrencyBalance(1L, BigDecimal.valueOf(1000), Currency.USD);
        currencyTransaction = new CurrencyTransaction(1L, LocalDate.now(), BigDecimal.valueOf(1000), Currency.USD, BigDecimal.valueOf(100));
        accountBalance = new AccountBalance(1L, BigDecimal.valueOf(2000));
        currencyTransactions.add(currencyTransaction);
        currencyBalances.add(currencyBalance);
    }

    @Test
    void shouldGetBalance() throws Exception {
        //given
        when(currencyBalanceDbService.getCurrencyBalance(Currency.USD)).thenReturn(currencyBalance);
        //when
        MvcResult mvcResult = mockMvc.perform(get("/v1/currency/balance/USD"))
                .andExpect(status().is(200))
                .andReturn();
        //then
        CurrencyBalanceDto currencyBalanceDto = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), CurrencyBalanceDto.class);
        assertThat(currencyBalanceDto).isNotNull();
        assertEquals(1L, currencyBalanceDto.getCurrencyBalanceId());
        assertEquals(Currency.USD, currencyBalanceDto.getCurrencyCode());
        assertEquals(BigDecimal.valueOf(1000), currencyBalanceDto.getBalance());
    }

    @Test
    void shouldGetBalanceList() throws Exception {
        //given
        when(currencyBalanceDbService.getAllCurrencyBalanceList()).thenReturn(currencyBalances);
        //when
        MvcResult mvcResult = mockMvc.perform(get("/v1/currency/balanceList"))
                .andExpect(status().is(200))
                .andReturn();
        //then
        List<CurrencyBalanceDto> currencyBalanceDtos = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ArrayList.class);
        assertThat(currencyBalanceDtos).isNotNull();
        assertEquals(1, currencyBalanceDtos.size());
    }

    @Test
    void shouldGetAllTransactions() throws Exception {
        //given
        when(currencyTransactionDbService.getAllTransactions()).thenReturn(currencyTransactions);
        //when
        MvcResult mvcResult = mockMvc.perform(get("/v1/currency/transactions"))
                .andExpect(status().is(200))
                .andReturn();
        //then
        List<CurrencyTransactionDto> currencyBalanceDtos = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ArrayList.class);
        assertThat(currencyBalanceDtos).isNotNull();
        assertEquals(1, currencyBalanceDtos.size());
    }

    @Test
    void shouldGetAllSavings() throws Exception {
        //given
        when(currencyBalanceDbService.getAllSavings()).thenReturn(BigDecimal.valueOf(1000));
        //when
        MvcResult mvcResult = mockMvc.perform(get("/v1/currency/all"))
                .andExpect(status().is(200))
                .andReturn();
        //then
        BigDecimal allSavings = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), BigDecimal.class);
        assertThat(allSavings).isNotNull();
        assertEquals(BigDecimal.valueOf(1000), allSavings);
    }

    @Test
    void shouldBuyCurrency() throws Exception {
        //given
        when(accountBalanceDbService.getAccountBalance()).thenReturn(accountBalance);
        //when&then
        MvcResult mvcResult = mockMvc.perform(post("/v1/currency/buy?accountValue=1000&currencyCode=USD&currencyValue=200"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void shouldSellCurrency() throws Exception {
        //given
        when(accountBalanceDbService.getAccountBalance()).thenReturn(accountBalance);
        //when&then
        MvcResult mvcResult = mockMvc.perform(post("/v1/currency/sell?accountValue=1000&currencyCode=USD&currencyValue=200"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
