package com.kodilla.savings.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodilla.savings.domain.CurrencyRates;
import com.kodilla.savings.domain.dto.CurrencyBalanceDto;
import com.kodilla.savings.domain.dto.CurrencyRatesDto;
import com.kodilla.savings.domain.enums.Currency;
import com.kodilla.savings.service.CurrencyRatesDbService;
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
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CurrencyRatesControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CurrencyRatesDbService currencyRatesDbService;
    private CurrencyRates currencyRates;
    private List<CurrencyRates> currencyRatesList = new ArrayList<>();

    @BeforeAll
    void setUp() {
        currencyRates = new CurrencyRates(1L, BigDecimal.valueOf(4.4), BigDecimal.valueOf(10), Currency.EUR);
        currencyRatesList.add(currencyRates);
    }

    @Test
    void shouldGetTopCryptoRates() throws Exception {
        //given
        when(currencyRatesDbService.getTopCurrencyRates()).thenReturn(currencyRatesList);
        //when
        MvcResult mvcResult = mockMvc.perform(get("/v1/currencyRates/top"))
                .andExpect(status().is(200))
                .andReturn();
        //then
        List<CurrencyRatesDto> currencyRatesDtoList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ArrayList.class);
        assertThat(currencyRatesDtoList).isNotNull();
        assertEquals(1, currencyRatesDtoList.size());
    }

    @Test
    void shouldGetAllCurrencyRates() throws Exception {
        //given
        when(currencyRatesDbService.getAllCurrencyRates()).thenReturn(currencyRatesList);
        //when
        MvcResult mvcResult = mockMvc.perform(get("/v1/currencyRates/ratesList"))
                .andExpect(status().is(200))
                .andReturn();
        //then
        List<CurrencyRatesDto> currencyRatesDtoList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ArrayList.class);
        assertThat(currencyRatesDtoList).isNotNull();
        assertEquals(1, currencyRatesDtoList.size());
    }
}
