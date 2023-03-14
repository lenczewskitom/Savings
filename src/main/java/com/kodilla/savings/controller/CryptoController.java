package com.kodilla.savings.controller;

import com.kodilla.savings.domain.*;
import com.kodilla.savings.domain.dto.CryptoBalanceDto;
import com.kodilla.savings.domain.dto.CryptoRatesDto;
import com.kodilla.savings.domain.dto.CryptoTransactionDto;
import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.domain.dto.coinapi.CoinApiResponseDto;
import com.kodilla.savings.domain.enums.DepositType;
import com.kodilla.savings.exception.NotEnoughCryptoException;
import com.kodilla.savings.exception.NotEnoughMoneyException;
import com.kodilla.savings.exception.TooManyRequestsException;
import com.kodilla.savings.mapper.CryptoBalanceMapper;
import com.kodilla.savings.mapper.CryptoRatesMapper;
import com.kodilla.savings.mapper.CryptoTransactionMapper;
import com.kodilla.savings.service.*;
import com.kodilla.savings.service.api.CoinApiDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final CoinApiDbService coinApiDbService;
    private final CryptoBalanceMapper cryptoBalanceMapper;
    private final CryptoTransactionMapper cryptoTransactionMapper;

    @GetMapping(value = "/rate/{cryptoCurrencyCode}")
    public ResponseEntity<CoinApiResponseDto> getRate(@PathVariable CryptoCurrency cryptoCurrencyCode) throws TooManyRequestsException {
        CoinApiResponseDto coinApiResponseDto = coinApiDbService.getCryptoRates(cryptoCurrencyCode);
        return new ResponseEntity<>(coinApiResponseDto, HttpStatus.OK);
    }

    @GetMapping(value = "/balance/{cryptoCurrencyCode}")
    public ResponseEntity<CryptoBalanceDto> getCryptoBalance(@PathVariable CryptoCurrency cryptoCurrencyCode) {
        CryptoBalance cryptoBalance = cryptoBalanceDbService.getCryptoBalance(cryptoCurrencyCode);
        return new ResponseEntity<>(cryptoBalanceMapper.mapToCryptoBalanceDto(cryptoBalance), HttpStatus.OK);
    }

    @GetMapping(value = "/balanceList")
    public ResponseEntity<List<CryptoBalanceDto>> getBalance() {
        List<CryptoBalance> cryptoBalanceDtoList = cryptoBalanceDbService.getAllCuryptoBalanceList();
        return new ResponseEntity<>(cryptoBalanceMapper.mapToCryptotBalanceDtoList(cryptoBalanceDtoList), HttpStatus.OK);
    }


    @GetMapping(value = "/transactions")
    public ResponseEntity<List<CryptoTransactionDto>> getAllTransactions() {
        List<CryptoTransaction> cryptoTransactions = cryptoTransactionDbService.getAllTransactions();
        return new ResponseEntity<>(cryptoTransactionMapper.mapToCryptoTransactionDtoList(cryptoTransactions), HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<BigDecimal> getAllSavings() throws TooManyRequestsException {
        BigDecimal savings = cryptoBalanceDbService.getAllSavings();
        return new ResponseEntity<>(savings, HttpStatus.OK);
    }

    @PostMapping(value = "/buy")
    public ResponseEntity<Void> buyCryptocurrency(@RequestParam BigDecimal accountValue, @RequestParam CryptoCurrency cryptoCurrencyCode,
                            @RequestParam BigDecimal cryptocurrencyValue) throws NotEnoughMoneyException, TooManyRequestsException {
        cryptoTransactionDbService.buyCryptocurrency(accountValue.negate(), cryptoCurrencyCode, cryptocurrencyValue);
        cryptoBalanceDbService.addCrypto(cryptoCurrencyCode, cryptocurrencyValue);
        accountBalanceDbService.updateAccountBalance(accountValue.negate());
        accountDepositDbService.withdrawDeposit(accountValue.negate(), DepositType.CRYPTOCURRENCY);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/sell")
    public ResponseEntity<Void> sellCryptocurrency(@RequestParam BigDecimal accountValue, @RequestParam CryptoCurrency cryptoCurrencyCode,
                                   @RequestParam BigDecimal cryptocurrencyValue) throws NotEnoughCryptoException {
        cryptoTransactionDbService.sellCryptocurrency(accountValue, cryptoCurrencyCode, cryptocurrencyValue.negate());
        cryptoBalanceDbService.withdrawCrypto(cryptoCurrencyCode, cryptocurrencyValue.negate());
        accountBalanceDbService.updateAccountBalance(accountValue);
        accountDepositDbService.addDeposit(accountValue, DepositType.CRYPTOCURRENCY);
        return ResponseEntity.ok().build();
    }
}
