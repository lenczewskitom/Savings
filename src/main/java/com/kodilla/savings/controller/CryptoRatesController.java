package com.kodilla.savings.controller;

import com.kodilla.savings.domain.CryptoRates;
import com.kodilla.savings.service.CryptoRatesDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "v1/cryptoRates")
@RequiredArgsConstructor
public class CryptoRatesController {

    private final CryptoRatesDbService cryptoRatesDbService;

    @GetMapping(value = "/top")
    public List<CryptoRates> getTopCryptoRates() {
        return cryptoRatesDbService.getTopCryptoRates();
    }

    @GetMapping(value = "/data")
    public String addData() {
        cryptoRatesDbService.addData();
        String str = "Ok";
        return str;
    }
}
