package com.kodilla.savings.controller;

import com.kodilla.savings.domain.AccountBalance;
import com.kodilla.savings.domain.AccountDeposit;
import com.kodilla.savings.domain.dto.AccountBalanceDto;
import com.kodilla.savings.domain.dto.AccountDepositDto;
import com.kodilla.savings.domain.enums.DepositType;
import com.kodilla.savings.exception.NotEnoughMoneyException;
import com.kodilla.savings.mapper.AccountBalanceMapper;
import com.kodilla.savings.mapper.AccountDepositMapper;
import com.kodilla.savings.service.AccountBalanceDbService;
import com.kodilla.savings.service.AccountDepositDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountBalanceDbService accountBalanceDbService;
    private final AccountDepositDbService accountDepositDbService;
    private final AccountDepositMapper accountDepositMapper;
    private final AccountBalanceMapper accountBalanceMapper;

    @GetMapping(value = "/deposits")
    public ResponseEntity<List<AccountDepositDto>> getAccountDeposits() {
        List<AccountDeposit> accountRequests = accountDepositDbService.getAccountDeposits();
        return new ResponseEntity<>(accountDepositMapper.mapToAccountDepositDtoList(accountRequests), HttpStatus.OK);
    }

    @GetMapping(value = "/balance")
    public ResponseEntity<AccountBalanceDto> getAccountBalance() {
        AccountBalance accountBalance = accountBalanceDbService.getAccountBalance();
        return new ResponseEntity<>(accountBalanceMapper.mapToAccountBalanceDto(accountBalance), HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Void> addDeposit(@RequestParam BigDecimal deposit) {
        accountDepositDbService.addDeposit(deposit, DepositType.ACCOUNT);
        accountBalanceDbService.updateAccountBalance(deposit);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/withdraw")
    public ResponseEntity<Void> withdrawDeposit(@RequestParam BigDecimal deposit) throws NotEnoughMoneyException {
        accountDepositDbService.withdrawDeposit(deposit.negate(), DepositType.ACCOUNT);
        accountBalanceDbService.updateAccountBalance(deposit.negate());
        return ResponseEntity.ok().build();
    }
}
