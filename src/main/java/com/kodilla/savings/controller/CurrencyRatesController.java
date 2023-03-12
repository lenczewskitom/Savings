package com.kodilla.savings.controller;

import com.kodilla.savings.domain.CurrencyRates;
import com.kodilla.savings.domain.dto.CurrencyRatesDto;
import com.kodilla.savings.mapper.CryptoRatesMapper;
import com.kodilla.savings.mapper.CurrencyRatesMapper;
import com.kodilla.savings.service.CurrencyRatesDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "v1/currencyRates")
@RequiredArgsConstructor
public class CurrencyRatesController {

    private final CurrencyRatesDbService currencyRatesDbService;
    private final CurrencyRatesMapper currencyRatesMapper;

    @GetMapping(value = "/top")
    public ResponseEntity<List<CurrencyRatesDto>> getTopCryptoRates() {
        List<CurrencyRates> currencyRates = currencyRatesDbService.getTopCurrencyRates();
        return new ResponseEntity<>(currencyRatesMapper.mapToCurrencyRatesDtoList(currencyRates), HttpStatus.OK);
    }

    @GetMapping(value = "/ratesList")
    public ResponseEntity<List<CurrencyRatesDto>> getAllCurrencyRates() {
        List<CurrencyRates> currencyRates = currencyRatesDbService.getAllCurrencyRates();
        return new ResponseEntity<>(currencyRatesMapper.mapToCurrencyRatesDtoList(currencyRates), HttpStatus.OK);
    }
}
