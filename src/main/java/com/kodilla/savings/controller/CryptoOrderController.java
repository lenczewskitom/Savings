package com.kodilla.savings.controller;

import com.kodilla.savings.domain.CryptoOrder;
import com.kodilla.savings.domain.CurrencyOrder;
import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.domain.enums.Order;
import com.kodilla.savings.exception.notFound.CryptoOrderNotFoundException;
import com.kodilla.savings.service.CryptoOrderDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "v1/cryptoOrder")
@RequiredArgsConstructor
public class CryptoOrderController {

    private final CryptoOrderDbService cryptoOrderDbService;

    @GetMapping
    public List<CryptoOrder> getAllCurrencyOrders() {
        return cryptoOrderDbService.getAllCryptoOrders();
    }

    @GetMapping(value = "/ordersValue")
    private BigDecimal getAllOrdersAccountValue() {
        return cryptoOrderDbService.getAllOrdersAccountValue();
    }

    @GetMapping(value = "/cryptoValue/{cryptoCode}")
    private BigDecimal getAllOrdersCryptoValue(@PathVariable CryptoCurrency cryptoCode) {
        return cryptoOrderDbService.getAllOrdersCryptoValue(cryptoCode);
    }

    @PostMapping
    public void addCryptoOrder(@RequestParam BigDecimal cryptoValue, @RequestParam CryptoCurrency cryptoCode,
                               @RequestParam BigDecimal cryptoRate, @RequestParam Order operationType) {
        cryptoOrderDbService.addCryptoOrder(cryptoValue, cryptoCode, cryptoRate, operationType);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCurrencyOrder(@PathVariable Long id) throws CryptoOrderNotFoundException {
        cryptoOrderDbService.deleteCryptoOrder(id);
    }
}
