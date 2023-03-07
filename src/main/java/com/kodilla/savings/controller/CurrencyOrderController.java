package com.kodilla.savings.controller;

import com.kodilla.savings.domain.CurrencyOrder;
import com.kodilla.savings.domain.dto.CurrencyOrderDto;
import com.kodilla.savings.domain.enums.Currency;
import com.kodilla.savings.domain.enums.Order;
import com.kodilla.savings.exception.notFound.CurrencyOrderNotFoundException;
import com.kodilla.savings.mapper.CurrencyOrderMapper;
import com.kodilla.savings.service.CurrencyOrderDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "v1/currencyOrder")
@RequiredArgsConstructor
public class CurrencyOrderController {

    private final CurrencyOrderDbService currencyOrderDbService;
    private final CurrencyOrderMapper currencyOrderMapper;

    @GetMapping("/allOrders")
    public ResponseEntity<List<CurrencyOrderDto>> getAllCurrencyOrders() {
        List<CurrencyOrder> currencyOrders = currencyOrderDbService.getAllCurrencyOrders();
        return new ResponseEntity<>(currencyOrderMapper.mapToCurrencyOrderDtoList(currencyOrders), HttpStatus.OK);
    }

    @GetMapping(value = "/ordersValue")
    private ResponseEntity<BigDecimal> getAllOrdersAccountValue() {
        BigDecimal value = currencyOrderDbService.getAllOrdersAccountValue();
        return new ResponseEntity<>(value, HttpStatus.OK);
    }

    @GetMapping(value = "/currencyValue/{currencyCode}")
    private ResponseEntity<BigDecimal> getAllOrdersCurrencyValue(@PathVariable Currency currencyCode) {
        BigDecimal value = currencyOrderDbService.getAllOrdersCurrencyValue(currencyCode);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }

    @PostMapping("/addOrder")
    public ResponseEntity<Void> addCurrencyOrder(@RequestParam BigDecimal currencyValue, @RequestParam Currency currencyCode,
                                 @RequestParam BigDecimal currencyRate, @RequestParam Order operationType) {
        currencyOrderDbService.addCurrencyOrder(currencyValue, currencyCode, currencyRate, operationType);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCurrencyOrder(@PathVariable Long id) throws CurrencyOrderNotFoundException {
        currencyOrderDbService.deleteCurrencyOrder(id);
        return ResponseEntity.ok().build();
    }
}
