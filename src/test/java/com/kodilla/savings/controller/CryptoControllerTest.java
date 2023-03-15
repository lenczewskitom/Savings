package com.kodilla.savings.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodilla.savings.domain.AccountBalance;
import com.kodilla.savings.domain.CryptoBalance;
import com.kodilla.savings.domain.CryptoTransaction;
import com.kodilla.savings.domain.dto.CryptoBalanceDto;
import com.kodilla.savings.domain.dto.CryptoTransactionDto;
import com.kodilla.savings.domain.dto.coinapi.CoinApiResponseDto;
import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.exception.TooManyRequestsException;
import com.kodilla.savings.service.AccountBalanceDbService;
import com.kodilla.savings.service.CryptoBalanceDbService;
import com.kodilla.savings.service.CryptoTransactionDbService;
import com.kodilla.savings.service.api.CoinApiDbService;
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
public class CryptoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CryptoBalanceDbService cryptoBalanceDbService;
    @MockBean
    private CryptoTransactionDbService cryptoTransactionDbService;
    @MockBean
    private AccountBalanceDbService accountBalanceDbService;
    @MockBean
    private CoinApiDbService coinApiDbService;
    private CryptoBalance cryptoBalance;
    private CryptoTransaction cryptoTransaction;
    private AccountBalance accountBalance;
    private CoinApiResponseDto coinApiResponseDto;
    private List<CryptoTransaction> cryptoTransactions = new ArrayList<>();
    private List<CryptoBalance> cryptoBalances = new ArrayList<>();
    @BeforeAll
    void setUp() {
        cryptoBalance = new CryptoBalance(1L, BigDecimal.valueOf(100), CryptoCurrency.SOL);
        cryptoTransaction = new CryptoTransaction(1L, LocalDate.now(), BigDecimal.valueOf(1000), CryptoCurrency.SOL, BigDecimal.valueOf(100));
        accountBalance = new AccountBalance(1L, BigDecimal.valueOf(2000));
        coinApiResponseDto = new CoinApiResponseDto(BigDecimal.valueOf(90));
        cryptoBalances.add(cryptoBalance);
        cryptoTransactions.add(cryptoTransaction);
    }

    @Test
    void shouldGetRate() throws Exception {
        //given
        when(coinApiDbService.getCryptoRates(CryptoCurrency.SOL)).thenReturn(coinApiResponseDto);
        //when
        MvcResult mvcResult = mockMvc.perform(get("/v1/cryptocurrency/rate/SOL"))
                .andExpect(status().is(200))
                .andReturn();
        //then
        CoinApiResponseDto coinApiResponseDto2 = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), CoinApiResponseDto.class);
        assertThat(coinApiResponseDto2).isNotNull();
        assertEquals(BigDecimal.valueOf(90), coinApiResponseDto2.getRate());
    }

    @Test
    void shouldGetCryptoBalance() throws Exception {
        //given
        when(cryptoBalanceDbService.getCryptoBalance(CryptoCurrency.SOL)).thenReturn(cryptoBalance);
        //when
        MvcResult mvcResult = mockMvc.perform(get("/v1/cryptocurrency/balance/SOL"))
                .andExpect(status().is(200))
                .andReturn();
        //then
        CryptoBalanceDto cryptoBalanceDto = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), CryptoBalanceDto.class);
        assertThat(cryptoBalanceDto).isNotNull();
        assertEquals(1L, cryptoBalanceDto.getCryptoBalanceId());
        assertEquals(BigDecimal.valueOf(100), cryptoBalanceDto.getBalance());
        assertEquals(CryptoCurrency.SOL, cryptoBalanceDto.getCryptocurrencyCode());
    }

    @Test
    void shouldGetBalance() throws Exception {
        //given
        when(cryptoBalanceDbService.getAllCuryptoBalanceList()).thenReturn(cryptoBalances);
        //when
        MvcResult mvcResult = mockMvc.perform(get("/v1/cryptocurrency/balanceList"))
                .andExpect(status().is(200))
                .andReturn();
        //then
        List<CryptoBalanceDto> cryptoBalanceDtos = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ArrayList.class);
        assertThat(cryptoBalanceDtos).isNotNull();
        assertEquals(1, cryptoBalanceDtos.size());
    }

    @Test
    void shouldGetAllSavings() throws Exception {
        //given
        when(cryptoBalanceDbService.getAllSavings()).thenReturn(BigDecimal.valueOf(1000));
        //when
        MvcResult mvcResult = mockMvc.perform(get("/v1/cryptocurrency/all"))
                .andExpect(status().is(200))
                .andReturn();
        //then
        BigDecimal allSavings = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), BigDecimal.class);
        assertThat(allSavings).isNotNull();
        assertEquals(BigDecimal.valueOf(1000), allSavings);
    }

    @Test
    void shouldSellCryptocurrency() throws Exception {
        //given
        when(cryptoBalanceDbService.getCryptoBalance(CryptoCurrency.SOL)).thenReturn(cryptoBalance);
        //when&then
        mockMvc.perform(post("/v1/cryptocurrency/sell?accountValue=500&cryptoCurrencyCode=SOL&cryptocurrencyValue=50"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldBuyCryptocurrency() throws Exception {
        //given
        when(accountBalanceDbService.getAccountBalance()).thenReturn(accountBalance);
        //when&then
        mockMvc.perform(post("/v1/cryptocurrency/buy?accountValue=1000&cryptoCurrencyCode=SOL&cryptocurrencyValue=100"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetAllTransactions() throws Exception {
        //given
        when(cryptoTransactionDbService.getAllTransactions()).thenReturn(cryptoTransactions);
        //when
        MvcResult mvcResult = mockMvc.perform(get("/v1/cryptocurrency/transactions"))
                .andExpect(status().is(200))
                .andReturn();
        //then
        List<CryptoTransactionDto> cryptoTransactionDtos = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ArrayList.class);
        assertThat(cryptoTransactionDtos).isNotNull();
        assertEquals(1, cryptoTransactionDtos.size());
    }
}
