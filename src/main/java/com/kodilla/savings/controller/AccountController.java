package com.kodilla.savings.controller;

import com.kodilla.savings.domain.AccountBalance;
import com.kodilla.savings.domain.AccountDeposit;
import com.kodilla.savings.domain.enums.DepositType;
import com.kodilla.savings.mapper.AccountDepositMapper;
import com.kodilla.savings.service.AccountBalanceDbService;
import com.kodilla.savings.service.AccountDepositDbService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping(value = "/deposits")
    public List<AccountDeposit> getAccountDeposits() {
        List<AccountDeposit> accountRequests = accountDepositDbService.getAccountDeposits();
        return accountRequests;
    }

    @GetMapping(value = "/balance")
    public AccountBalance getAccountBalance() {
        return accountBalanceDbService.getAccountBalance();
    }

    @PostMapping()
    public void addDeposit(@RequestParam BigDecimal deposit) {
        accountDepositDbService.addDeposit(deposit, DepositType.ACCOUNT);
        accountBalanceDbService.updateAccountBalance(deposit);
    }
}
