package com.kodilla.savings.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodilla.savings.domain.CryptoRates;
import com.kodilla.savings.domain.dto.CryptoOrderDto;
import com.kodilla.savings.domain.dto.CryptoRatesDto;
import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.service.CryptoRatesDbService;
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
public class CryptoRatesControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CryptoRatesDbService cryptoRatesDbService;
    private CryptoRates cryptoRates;
    private List<CryptoRates> cryptoRatesList = new ArrayList<>();

    @BeforeAll
    void setUp() {
        cryptoRates = new CryptoRates(1L, BigDecimal.valueOf(90), BigDecimal.valueOf(10), CryptoCurrency.SOL);
        cryptoRatesList.add(cryptoRates);
    }

    @Test
    void shouldGetTopCryptoRates() throws Exception {
        //given
        when(cryptoRatesDbService.getTopCryptoRates()).thenReturn(cryptoRatesList);
        //when
        MvcResult mvcResult = mockMvc.perform(get("/v1/cryptoRates/top"))
                .andExpect(status().is(200))
                .andReturn();
        //then
        List<CryptoRatesDto> cryptoRatesDtoList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ArrayList.class);
        assertThat(cryptoRatesDtoList).isNotNull();
        assertEquals(1, cryptoRatesDtoList.size());
    }

    @Test
    void shouldGetAllCryptoRates() throws Exception {
        //given
        when(cryptoRatesDbService.getAllCryptoRates()).thenReturn(cryptoRatesList);
        //when
        MvcResult mvcResult = mockMvc.perform(get("/v1/cryptoRates/ratesList"))
                .andExpect(status().is(200))
                .andReturn();
        //then
        List<CryptoRatesDto> cryptoRatesDtoList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ArrayList.class);
        assertThat(cryptoRatesDtoList).isNotNull();
        assertEquals(1, cryptoRatesDtoList.size());
    }
}
