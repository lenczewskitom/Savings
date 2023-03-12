package com.kodilla.savings.controller;

import com.kodilla.savings.domain.CryptoOrder;
import com.kodilla.savings.domain.dto.CryptoOrderDto;
import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.domain.enums.Order;
import com.kodilla.savings.exception.NotEnoughCryptoException;
import com.kodilla.savings.exception.NotEnoughMoneyException;
import com.kodilla.savings.exception.notFound.CryptoOrderNotFoundException;
import com.kodilla.savings.mapper.CryptoOrderMapper;
import com.kodilla.savings.service.CryptoOrderDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "v1/cryptoOrder")
@RequiredArgsConstructor
public class CryptoOrderController {

    private final CryptoOrderDbService cryptoOrderDbService;
    private final CryptoOrderMapper cryptoOrderMapper;

    @GetMapping
    public ResponseEntity<List<CryptoOrderDto>> getAllCurrencyOrders() {
        List<CryptoOrder> cryptoOrders = cryptoOrderDbService.getAllCryptoOrders();
        return new ResponseEntity<>(cryptoOrderMapper.mapToCryptoOrderDtoList(cryptoOrders), HttpStatus.OK);
    }

    @GetMapping(value = "/ordersValue")
    private ResponseEntity<BigDecimal> getAllOrdersAccountValue() {
        BigDecimal value = cryptoOrderDbService.getAllOrdersAccountValue();
        return new ResponseEntity<>(value, HttpStatus.OK);
    }

    @GetMapping(value = "/cryptoValue/{cryptoCode}")
    private ResponseEntity<BigDecimal> getAllOrdersCryptoValue(@PathVariable CryptoCurrency cryptoCode) {
        BigDecimal value = cryptoOrderDbService.getAllOrdersCryptoValue(cryptoCode);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addCryptoOrder(@RequestParam BigDecimal cryptoValue, @RequestParam CryptoCurrency cryptoCode,
                               @RequestParam BigDecimal cryptoRate, @RequestParam Order operationType) throws NotEnoughCryptoException, NotEnoughMoneyException {
        cryptoOrderDbService.addCryptoOrder(cryptoValue, cryptoCode, cryptoRate, operationType);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCurrencyOrder(@PathVariable Long id) throws CryptoOrderNotFoundException {
        cryptoOrderDbService.deleteCryptoOrder(id);
        return ResponseEntity.ok().build();
    }
}
