package com.kodilla.savings.controller;

import com.kodilla.savings.domain.CryptoRates;
import com.kodilla.savings.domain.dto.CryptoRatesDto;
import com.kodilla.savings.mapper.CryptoRatesMapper;
import com.kodilla.savings.service.CryptoRatesDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Vector;

@RestController
@RequestMapping(value = "v1/cryptoRates")
@RequiredArgsConstructor
public class CryptoRatesController {

    private final CryptoRatesDbService cryptoRatesDbService;
    private final CryptoRatesMapper cryptoRatesMapper;


    @GetMapping(value = "/top")
    public ResponseEntity<List<CryptoRatesDto>> getTopCryptoRates() {
        List<CryptoRates> cryptoRates = cryptoRatesDbService.getTopCryptoRates();
        return new ResponseEntity<>(cryptoRatesMapper.mapToCryptotRatesDtoList(cryptoRates), HttpStatus.OK);
    }

    @GetMapping(value = "/ratesList")
    public ResponseEntity<List<CryptoRatesDto>> getAllCryptoRates() {
        List<CryptoRates> cryptoRatesList = cryptoRatesDbService.getAllCryptoRates();
        return new ResponseEntity<>(cryptoRatesMapper.mapToCryptotRatesDtoList(cryptoRatesList), HttpStatus.OK);
    }
}
