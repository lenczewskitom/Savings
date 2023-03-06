package com.kodilla.savings.controller;

import com.kodilla.savings.domain.CurrencyOrder;
import com.kodilla.savings.domain.enums.Currency;
import com.kodilla.savings.domain.enums.Order;
import com.kodilla.savings.exception.notFound.CurrencyOrderNotFoundException;
import com.kodilla.savings.service.CurrencyOrderDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "v1/currencyOrder")
@RequiredArgsConstructor
public class CurrencyOrderController {

    private final CurrencyOrderDbService currencyOrderDbService;

    @GetMapping
    public List<CurrencyOrder> getAllCurrencyOrders() {
        return currencyOrderDbService.getAllCurrencyOrders();
    }

    @GetMapping(value = "/ordersValue")
    private BigDecimal getAllOrdersAccountValue() {
        return currencyOrderDbService.getAllOrdersAccountValue();
    }

    @GetMapping(value = "/currencyValue/{currencyCode}")
    private BigDecimal getAllOrdersCurrencyValue(@PathVariable Currency currencyCode) {
        return currencyOrderDbService.getAllOrdersCurrencyValue(currencyCode);
    }

    @PostMapping
    public void addCurrencyOrder(@RequestParam BigDecimal currencyValue, @RequestParam Currency currencyCode,
                                 @RequestParam BigDecimal currencyRate, @RequestParam Order operationType) {
        currencyOrderDbService.addCurrencyOrder(currencyValue, currencyCode, currencyRate, operationType);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCurrencyOrder(@PathVariable Long id) throws CurrencyOrderNotFoundException {
        currencyOrderDbService.deleteCurrencyOrder(id);
    }
}
