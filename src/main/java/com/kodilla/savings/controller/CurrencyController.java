package com.kodilla.savings.controller;

import com.kodilla.savings.domain.CurrencyBalance;
import com.kodilla.savings.domain.CurrencyTransaction;
import com.kodilla.savings.domain.dto.nbp.RatesDto;
import com.kodilla.savings.domain.enums.Currency;
import com.kodilla.savings.service.AccountBalanceDbService;
import com.kodilla.savings.service.AccountDepositDbService;
import com.kodilla.savings.service.CurrencyBalanceDbService;
import com.kodilla.savings.service.CurrencyTransactionDbService;
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

    @GetMapping(value = "/transactions")
    public List<CurrencyTransaction> getAllTransactions() {
        return currencyTransactionDbService.getAllTransactions();
    }

    @PostMapping(value = "/buy")
    public void buyCurrency(@RequestParam BigDecimal accountValue, @RequestParam Currency currencyCode,
                               @RequestParam BigDecimal currencyValue) {
        currencyTransactionDbService.buyCurrency(accountValue, currencyCode, currencyValue);
        currencyBalanceDbService.updateCurrencyBalance(currencyCode, currencyValue);
        accountBalanceDbService.updateAccountBalance(accountValue.negate());
        accountDepositDbService.addDeposit(accountValue.negate());
    }

    @PostMapping(value = "/sell")
    public void sellCurrency(@RequestParam BigDecimal accountValue, @RequestParam Currency currencyCode,
                             @RequestParam BigDecimal currencyValue) {
        //dbService.sellCurrency(accountValue, currencyCode, currencyValue);
    }
}
