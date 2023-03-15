package com.kodilla.savings.controller;

import com.kodilla.savings.domain.CurrencyBalance;
import com.kodilla.savings.domain.CurrencyRates;
import com.kodilla.savings.domain.CurrencyTransaction;
import com.kodilla.savings.domain.dto.CurrencyBalanceDto;
import com.kodilla.savings.domain.dto.CurrencyRatesDto;
import com.kodilla.savings.domain.dto.CurrencyTransactionDto;
import com.kodilla.savings.domain.dto.nbp.RatesDto;
import com.kodilla.savings.domain.enums.Currency;
import com.kodilla.savings.domain.enums.DepositType;
import com.kodilla.savings.exception.NotEnoughCurrencyException;
import com.kodilla.savings.exception.NotEnoughMoneyException;
import com.kodilla.savings.mapper.CurrencyBalanceMapper;
import com.kodilla.savings.mapper.CurrencyTransactionMapper;
import com.kodilla.savings.service.*;
import com.kodilla.savings.service.api.NbpApiDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final CurrencyBalanceMapper currencyBalanceMapper;
    private final CurrencyTransactionMapper currencyTransactionMapper;

    @GetMapping(value = "/rate")
    public ResponseEntity<RatesDto> getRate(@RequestParam Currency currency) {
        RatesDto ratesDto = nbpApiDbService.getRates(currency);
        return new ResponseEntity<>(ratesDto, HttpStatus.OK);
    }

    @GetMapping(value = "/balance/{currencyCode}")
    public ResponseEntity<CurrencyBalanceDto> getBalance(@PathVariable Currency currencyCode) {
        CurrencyBalance currencyBalance = currencyBalanceDbService.getCurrencyBalance(currencyCode);
        return new ResponseEntity<>(currencyBalanceMapper.mapToCurrencyBalanceDto(currencyBalance), HttpStatus.OK);
    }

    @GetMapping(value = "/balanceList")
    public ResponseEntity<List<CurrencyBalanceDto>> getBalanceList() {
        List<CurrencyBalance> currencyBalances = currencyBalanceDbService.getAllCurrencyBalanceList();
        return new ResponseEntity<>(currencyBalanceMapper.mapToCurrencyBalanceDtoList(currencyBalances), HttpStatus.OK);
    }

    @GetMapping(value = "/transactions")
    public ResponseEntity<List<CurrencyTransactionDto>> getAllTransactions() {
        List<CurrencyTransaction> currencyTransactions = currencyTransactionDbService.getAllTransactions();
        return new ResponseEntity<>(currencyTransactionMapper.mapToCurrencyTransactionDtoList(currencyTransactions), HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<BigDecimal> getAllSavings() {
        BigDecimal savings = currencyBalanceDbService.getAllSavings();
        return new ResponseEntity<>(savings, HttpStatus.OK);
    }

    @PostMapping(value = "/buy")
    public ResponseEntity<Void> buyCurrency(@RequestParam BigDecimal accountValue, @RequestParam Currency currencyCode,
                               @RequestParam BigDecimal currencyValue) throws NotEnoughMoneyException {
        currencyTransactionDbService.buyCurrency(accountValue.negate(), currencyCode, currencyValue);
        currencyBalanceDbService.updateCurrencyBalance(currencyCode, currencyValue);
        accountBalanceDbService.updateAccountBalance(accountValue.negate());
        accountDepositDbService.withdrawDeposit(accountValue.negate(), DepositType.CURRENCY);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/sell")
    public ResponseEntity<Void> sellCurrency(@RequestParam BigDecimal accountValue, @RequestParam Currency currencyCode,
                                               @RequestParam BigDecimal currencyValue) throws NotEnoughCurrencyException {
        currencyTransactionDbService.sellCurrency(accountValue, currencyCode, currencyValue.negate());
        currencyBalanceDbService.updateCurrencyBalance(currencyCode, currencyValue.negate());
        accountBalanceDbService.updateAccountBalance(accountValue);
        accountDepositDbService.addDeposit(accountValue, DepositType.CURRENCY);
        return ResponseEntity.ok().build();
    }
}
