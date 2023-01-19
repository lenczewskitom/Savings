package com.kodilla.savings.controller;

import com.kodilla.savings.domain.CryptoCurrencyBalance;
import com.kodilla.savings.domain.CryptoCurrencyRequest;
import com.kodilla.savings.domain.dto.nbp.RatesDto;
import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("v1/cryptocurrency")
@RequiredArgsConstructor
public class CryptoCurrencyController {

    private final DbService dbService;

    @GetMapping(value = "/rate")
    public RatesDto getRate(@RequestParam CryptoCurrency cryptoCurrencyCode) {

        //return dbService.getCryptoRates(cryptoCurrencyCode);
        return null;
    }

    @GetMapping(value = "/balance/{cryptocurrencyCode}")
    public CryptoCurrencyBalance getBalance(@PathVariable CryptoCurrency cryptoCurrencyCode) {
        return dbService.getCryptocurrencyBalance(cryptoCurrencyCode);
    }

    @GetMapping(value = "/transactions")
    public List<CryptoCurrencyRequest> getAllTransactions() {
        return dbService.getAllCryptoCurrencyTransactions();
    }

    @PostMapping(value = "/buy")
    public void buyCryptocurrency(@RequestParam BigDecimal accountValue, @RequestParam CryptoCurrency cryptoCurrencyCode,
                            @RequestParam BigDecimal cryptocurrencyValue) {
        dbService.buyCryptocurrency(accountValue, cryptoCurrencyCode, cryptocurrencyValue);
    }

    @PostMapping(value = "/sell")
    public void sellCryptocurrency(@RequestParam BigDecimal accountValue, @RequestParam CryptoCurrency cryptoCurrencyCode,
                                   @RequestParam BigDecimal cryptocurrencyValue) {
        dbService.sellCryptocurrency(accountValue, cryptoCurrencyCode, cryptocurrencyValue);
    }
}
