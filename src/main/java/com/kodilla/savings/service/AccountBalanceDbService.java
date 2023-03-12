package com.kodilla.savings.service;

import com.kodilla.savings.domain.AccountBalance;
import com.kodilla.savings.repository.AccountBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountBalanceDbService {

    private final AccountBalanceRepository accountBalanceRepository;

    public AccountBalance getAccountBalance(){
        return accountBalanceRepository.getLastAccountBalance();
    }

    public void updateAccountBalance(BigDecimal deposit) {
        BigDecimal newBalance = accountBalanceRepository.getLastAccountBalance().getBalance().add(deposit);
        AccountBalance accountBalance = new AccountBalance(newBalance);
        accountBalanceRepository.save(accountBalance);
    }
}
