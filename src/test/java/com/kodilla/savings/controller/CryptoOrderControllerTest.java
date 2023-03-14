package com.kodilla.savings.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodilla.savings.domain.CryptoOrder;
import com.kodilla.savings.domain.dto.CryptoBalanceDto;
import com.kodilla.savings.domain.dto.CryptoOrderDto;
import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.domain.enums.Order;
import com.kodilla.savings.service.CryptoOrderDbService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CryptoOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    CryptoOrderDbService cryptoOrderDbService;
    private CryptoOrder cryptoOrder;
    List<CryptoOrder> cryptoOrders = new ArrayList<>();

    @BeforeAll
    void setUp() {
        cryptoOrder = new CryptoOrder(1L, LocalDate.now(), BigDecimal.valueOf(100), CryptoCurrency.SOL, BigDecimal.valueOf(90), Order.BUY);
        cryptoOrders.add(cryptoOrder);
    }

    @Test
    void shouldGetAllCryptoOrders() throws Exception {
        //given
        when(cryptoOrderDbService.getAllCryptoOrders()).thenReturn(cryptoOrders);
        //when
        MvcResult mvcResult = mockMvc.perform(get("/v1/cryptoOrder"))
                .andExpect(status().is(200))
                .andReturn();
        //then
        List<CryptoOrderDto> cryptoOrderDtos = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ArrayList.class);
        assertThat(cryptoOrderDtos).isNotNull();
        assertEquals(1, cryptoOrderDtos.size());
    }

    @Test
    void shouldGetAllOrdersAccountValue() throws Exception {
        //given
        when(cryptoOrderDbService.getAllOrdersAccountValue()).thenReturn(BigDecimal.valueOf(1000));
        //when
        MvcResult mvcResult = mockMvc.perform(get("/v1/cryptoOrder/ordersValue"))
                .andExpect(status().is(200))
                .andReturn();
        //then
        BigDecimal accountValue = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), BigDecimal.class);
        assertThat(accountValue).isNotNull();
        assertEquals(BigDecimal.valueOf(1000), accountValue);
    }

    @Test
    void shouldGetAllOrdersCryptoValue() throws Exception {
        //given
        when(cryptoOrderDbService.getAllOrdersCryptoValue(CryptoCurrency.SOL)).thenReturn(BigDecimal.valueOf(100));
        //when
        MvcResult mvcResult = mockMvc.perform(get("/v1/cryptoOrder/cryptoValue/SOL"))
                .andExpect(status().is(200))
                .andReturn();
        //then
        BigDecimal cryptoValue = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), BigDecimal.class);
        assertThat(cryptoValue).isNotNull();
        assertEquals(BigDecimal.valueOf(100), cryptoValue);
    }

    @Test
    void shouldAddCryptoOrder() throws Exception {
        //given
        //when&then
        MvcResult mvcResult = mockMvc.perform(post("/v1/cryptoOrder?cryptoValue=50&cryptoCode=SOL&cryptoRate=90&operationType=BUY"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void shouldDeleteCurrencyOrder() throws Exception {
        //given
        //when&then
        MvcResult mvcResult = mockMvc.perform(delete("/v1/cryptoOrder/1"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
