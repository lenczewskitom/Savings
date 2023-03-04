package com.kodilla.savings.service;

import com.kodilla.savings.domain.AccountDeposit;
import com.kodilla.savings.domain.enums.DepositType;
import com.kodilla.savings.repository.AccountDepositRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountDepositDbService {

    private final AccountDepositRepository accountDepositRepository;

    @Cacheable(cacheNames = "AccountDeposits")
    public List<AccountDeposit> getAccountDeposits() {
        return accountDepositRepository.reverseDepositsList();
    }

    @CacheEvict(cacheNames = "AccountDeposits", allEntries = true)
    public void addDeposit(BigDecimal deposit, DepositType depositType) {
        AccountDeposit accountDeposit = new AccountDeposit(deposit, depositType);
        accountDepositRepository.save(accountDeposit);
    }
}
