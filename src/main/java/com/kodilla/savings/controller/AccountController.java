package com.kodilla.savings.controller;

import com.kodilla.savings.domain.AccountBalance;
import com.kodilla.savings.domain.AccountRequest;
import com.kodilla.savings.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final DbService dbService;

    @GetMapping(value = "/deposits")
    public List<AccountRequest> getAccountDeposits() {
        List<AccountRequest> accountRequests = dbService.getAllAccountDeposits();
        return accountRequests;
    }

    @GetMapping(value = "/balance")
    public AccountBalance getAccountBalance() {
        return dbService.getAccountBalance();
    }

    @PostMapping()
    public void addDeposit(@RequestParam BigDecimal deposit) {
        dbService.addDeposit(deposit);
    }
}
