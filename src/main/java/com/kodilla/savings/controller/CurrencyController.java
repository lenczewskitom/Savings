package com.kodilla.savings.controller;

import com.kodilla.savings.domain.CurrencyBalance;
import com.kodilla.savings.domain.CurrencyRequest;
import com.kodilla.savings.domain.dto.nbp.RatesDto;
import com.kodilla.savings.domain.enums.Currency;
import com.kodilla.savings.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("v1/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final DbService dbService;
    @GetMapping(value = "/rate")
    public RatesDto getRate(@RequestParam Currency currency) {

        return dbService.getRates(currency);
    }

    @GetMapping(value = "/balance/{currencyCode}")
    public CurrencyBalance getBalance(@PathVariable Currency currencyCode) {
        return dbService.getCurrencyBalance(currencyCode);
    }

    @GetMapping(value = "/transactions")
    public List<CurrencyRequest> getAllTransactions() {
        return dbService.getAllCurrencyTransactions();
    }

    @PostMapping(value = "/buy")
    public void buyCurrency(@RequestParam BigDecimal accountValue, @RequestParam Currency currencyCode,
                               @RequestParam BigDecimal currencyValue) {
        dbService.buyCurrency(accountValue, currencyCode, currencyValue);
    }

    @PostMapping(value = "/sell")
    public void sellCurrency(@RequestParam BigDecimal accountValue, @RequestParam Currency currencyCode,
                             @RequestParam BigDecimal currencyValue) {
        dbService.sellCurrency(accountValue, currencyCode, currencyValue);
    }
}
