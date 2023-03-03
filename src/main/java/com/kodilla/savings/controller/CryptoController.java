package com.kodilla.savings.controller;

import com.kodilla.savings.domain.*;
import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.domain.dto.coinapi.CoinApiResponseDto;
import com.kodilla.savings.service.*;
import com.kodilla.savings.service.api.CoinApiDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("v1/cryptocurrency")
@RequiredArgsConstructor
public class CryptoController {

    private final CryptoBalanceDbService cryptoBalanceDbService;
    private final CryptoTransactionDbService cryptoTransactionDbService;
    private final AccountBalanceDbService accountBalanceDbService;
    private final AccountDepositDbService accountDepositDbService;
    private final CryptoRatesDbService cryptoRatesDbService;
    private final CoinApiDbService coinApiDbService;

    @GetMapping(value = "/rate/{cryptoCurrencyCode}")
    public CoinApiResponseDto getRate(@PathVariable CryptoCurrency cryptoCurrencyCode) {

        return coinApiDbService.getCryptoRates(cryptoCurrencyCode);
    }

    @GetMapping(value = "/data")
    public String addData() {
        String str = "Ok";
        cryptoBalanceDbService.addData();
        return str;
    }

    @GetMapping(value = "/balance/{cryptoCurrencyCode}")
    public CryptoBalance getCryptoBalance(@PathVariable CryptoCurrency cryptoCurrencyCode) {
        return cryptoBalanceDbService.getCryptoBalance(cryptoCurrencyCode);
    }

    @GetMapping(value = "/balanceList")
    public List<CryptoBalance> getBalance() {
        return cryptoBalanceDbService.getAllCuryptoBalanceList();
    }

    @GetMapping(value = "/ratesList")
    public List<CryptoRates> getAllCurrencyRates() {
        return cryptoRatesDbService.getAllCryptoRates();
    }

    @GetMapping(value = "/transactions")
    public List<CryptoTransaction> getAllTransactions() {

        return cryptoTransactionDbService.getAllTransactions();
    }

    @GetMapping(value = "/all")
    public BigDecimal getAllSavings() {
        return cryptoBalanceDbService.getAllSavings();
    }

    @PostMapping(value = "/buy")
    public void buyCryptocurrency(@RequestParam BigDecimal accountValue, @RequestParam CryptoCurrency cryptoCurrencyCode,
                            @RequestParam BigDecimal cryptocurrencyValue) {
        cryptoTransactionDbService.buyCryptocurrency(accountValue.negate(), cryptoCurrencyCode, cryptocurrencyValue);
        cryptoBalanceDbService.updateCryptoBalance(cryptoCurrencyCode, cryptocurrencyValue);
        accountBalanceDbService.updateAccountBalance(accountValue.negate());
        accountDepositDbService.addDeposit(accountValue.negate());
    }

    @PostMapping(value = "/sell")
    public void sellCryptocurrency(@RequestParam BigDecimal accountValue, @RequestParam CryptoCurrency cryptoCurrencyCode,
                                   @RequestParam BigDecimal cryptocurrencyValue) {
        cryptoTransactionDbService.sellCryptocurrency(accountValue, cryptoCurrencyCode, cryptocurrencyValue.negate());
        cryptoBalanceDbService.updateCryptoBalance(cryptoCurrencyCode, cryptocurrencyValue.negate());
        accountBalanceDbService.updateAccountBalance(accountValue);
        accountDepositDbService.addDeposit(accountValue);
    }
}
