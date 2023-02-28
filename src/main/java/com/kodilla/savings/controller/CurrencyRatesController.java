package com.kodilla.savings.controller;

import com.kodilla.savings.domain.CurrencyRates;
import com.kodilla.savings.service.CurrencyRatesDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "v1/currencyRates")
@RequiredArgsConstructor
public class CurrencyRatesController {

    private final CurrencyRatesDbService currencyRatesDbService;

    @GetMapping(value = "/top")
    public List<CurrencyRates> getTopCryptoRates() {
        return currencyRatesDbService.getTopCurrencyRates();
    }

    @GetMapping(value = "/data")
    public String addData() {
        currencyRatesDbService.addData();
        String str = "Ok";
        return str;
    }
}
