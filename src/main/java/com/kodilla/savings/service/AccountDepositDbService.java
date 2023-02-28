package com.kodilla.savings.service;

import com.kodilla.savings.domain.AccountDeposit;
import com.kodilla.savings.repository.AccountDepositRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountDepositDbService {

    private final AccountDepositRepository accountDepositRepository;

    public List<AccountDeposit> getAccountDeposits() {
        return accountDepositRepository.reverseDepositsList();
    }

    public void addDeposit(BigDecimal deposit) {
        AccountDeposit accountDeposit = new AccountDeposit(deposit);
        accountDepositRepository.save(accountDeposit);
    }
}