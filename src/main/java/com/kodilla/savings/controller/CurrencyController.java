package com.kodilla.savings.controller;

import com.kodilla.savings.domain.CurrencyBalance;
import com.kodilla.savings.domain.CurrencyRates;
import com.kodilla.savings.domain.CurrencyTransaction;
import com.kodilla.savings.domain.dto.nbp.RatesDto;
import com.kodilla.savings.domain.enums.Currency;
import com.kodilla.savings.service.*;
import com.kodilla.savings.service.api.NbpApiDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("v1/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyBalanceDbService currencyBalanceDbService;
    private final CurrencyTransactionDbService currencyTransactionDbService;
    private final CurrencyRatesDbService currencyRatesDbService;
    private final AccountBalanceDbService accountBalanceDbService;
    private final AccountDepositDbService accountDepositDbService;
    private final NbpApiDbService nbpApiDbService;
    @GetMapping(value = "/rate")
    public RatesDto getRate(@RequestParam Currency currency) {

        return nbpApiDbService.getRates(currency);
    }

    @GetMapping(value = "/data")
    public String addData() {
        String str = "Ok";
        currencyBalanceDbService.addData();
        return str;
    }

    @GetMapping(value = "/balance/{currencyCode}")
    public CurrencyBalance getBalance(@PathVariable Currency currencyCode) {
        return currencyBalanceDbService.getCurrencyBalance(currencyCode);
    }

    @GetMapping(value = "/balanceList")
    public List<CurrencyBalance> getBalance() {
        return currencyBalanceDbService.getAllCurrencyBalanceList();
    }

    @GetMapping(value = "/ratesList")
    public List<CurrencyRates> getAllCurrencyRates() {
        return currencyRatesDbService.getAllCurrencyRates();
    }

    @GetMapping(value = "/transactions")
    public List<CurrencyTransaction> getAllTransactions() {
        return currencyTransactionDbService.getAllTransactions();
    }

    @GetMapping(value = "/all")
    public BigDecimal getAllSavings() {
        return currencyBalanceDbService.getAllSavings();
    }

    @PostMapping(value = "/buy")
    public void buyCurrency(@RequestParam BigDecimal accountValue, @RequestParam Currency currencyCode,
                               @RequestParam BigDecimal currencyValue) {
        currencyTransactionDbService.buyCurrency(accountValue.negate(), currencyCode, currencyValue);
        currencyBalanceDbService.updateCurrencyBalance(currencyCode, currencyValue);
        accountBalanceDbService.updateAccountBalance(accountValue.negate());
        accountDepositDbService.addDeposit(accountValue.negate());
    }

    @PostMapping(value = "/sell")
    public void sellCurrency(@RequestParam BigDecimal accountValue, @RequestParam Currency currencyCode,
                             @RequestParam BigDecimal currencyValue) {
        currencyTransactionDbService.sellCurrency(accountValue, currencyCode, currencyValue.negate());
        currencyBalanceDbService.updateCurrencyBalance(currencyCode, currencyValue.negate());
        accountBalanceDbService.updateAccountBalance(accountValue);
        accountDepositDbService.addDeposit(accountValue);
    }
}
