package com.kodilla.savings.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodilla.savings.domain.CurrencyOrder;
import com.kodilla.savings.domain.dto.CurrencyBalanceDto;
import com.kodilla.savings.domain.dto.CurrencyOrderDto;
import com.kodilla.savings.domain.enums.Currency;
import com.kodilla.savings.domain.enums.Order;
import com.kodilla.savings.service.CurrencyOrderDbService;
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
public class CurrencyOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CurrencyOrderDbService currencyOrderDbService;
    private CurrencyOrder currencyOrder;
    private List<CurrencyOrder> currencyOrders = new ArrayList<>();

    @BeforeAll
    void setUp() {
        currencyOrder = new CurrencyOrder(1L, LocalDate.now(), BigDecimal.valueOf(200), Currency.EUR, BigDecimal.valueOf(4.5), Order.BUY);
        currencyOrders.add(currencyOrder);
    }

    @Test
    void shouldGetAllCurrencyOrders() throws Exception {
        //given
        when(currencyOrderDbService.getAllCurrencyOrders()).thenReturn(currencyOrders);
        //when
        MvcResult mvcResult = mockMvc.perform(get("/v1/currencyOrder/allOrders"))
                .andExpect(status().is(200))
                .andReturn();
        //then
        List<CurrencyOrderDto> currencyOrderDtos = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ArrayList.class);
        assertThat(currencyOrderDtos).isNotNull();
        assertEquals(1, currencyOrderDtos.size());
    }

    @Test
    void shouldGetAllOrdersAccountValue() throws Exception {
        //given
        when(currencyOrderDbService.getAllOrdersAccountValue()).thenReturn(BigDecimal.valueOf(1000));
        //when
        MvcResult mvcResult = mockMvc.perform(get("/v1/currencyOrder/ordersValue"))
                .andExpect(status().is(200))
                .andReturn();
        //then
        BigDecimal accountValue = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), BigDecimal.class);
        assertThat(accountValue).isNotNull();
        assertEquals(BigDecimal.valueOf(1000), accountValue);
    }

    @Test
    void shouldGetAllOrdersCurrencyValue() throws Exception {
        //given
        when(currencyOrderDbService.getAllOrdersCurrencyValue(Currency.EUR)).thenReturn(BigDecimal.valueOf(200));
        //when
        MvcResult mvcResult = mockMvc.perform(get("/v1/currencyOrder/currencyValue/EUR"))
                .andExpect(status().is(200))
                .andReturn();
        //then
        BigDecimal currencyValue = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), BigDecimal.class);
        assertThat(currencyValue).isNotNull();
        assertEquals(BigDecimal.valueOf(200), currencyValue);
    }

    @Test
    void shouldAddCurrencyOrder() throws Exception {
        //given
        //when&then
        MvcResult mvcResult = mockMvc.perform(post("/v1/currencyOrder/addOrder?currencyValue=200&currencyCode=EUR&currencyRate=4.5&operationType=BUY"))
                .andExpect(status().is(200))
                .andReturn();
    }

    @Test
    void shouldDeleteCurrencyOrder() throws Exception {
        //given
        //when&then
        MvcResult mvcResult = mockMvc.perform(delete("/v1/currencyOrder/1"))
                .andExpect(status().is(200))
                .andReturn();
    }
}
