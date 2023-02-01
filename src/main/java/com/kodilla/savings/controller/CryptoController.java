package com.kodilla.savings.controller;

import com.kodilla.savings.domain.CryptoTransaction;
import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.domain.coinapi.CoinApiResponseDto;
import com.kodilla.savings.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("v1/cryptocurrency")
@RequiredArgsConstructor
public class CryptoController {

    private final DbService dbService;

    @GetMapping(value = "/rate/{cryptoCurrencyCode}")
    public CoinApiResponseDto getRate(@PathVariable CryptoCurrency cryptoCurrencyCode) {

        return dbService.getCryptoRates(cryptoCurrencyCode);
    }

    @GetMapping(value = "/transactions")
    public List<CryptoTransaction> getAllTransactions() {
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
