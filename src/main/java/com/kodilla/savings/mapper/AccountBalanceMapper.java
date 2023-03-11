package com.kodilla.savings.mapper;

import com.kodilla.savings.domain.AccountBalance;
import com.kodilla.savings.domain.dto.AccountBalanceDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountBalanceMapper {

    public AccountBalanceDto mapToAccountBalanceDto(final AccountBalance accountBalance) {
        return new AccountBalanceDto(
                accountBalance.getAccountBalanceId(),
                accountBalance.getBalance()
        );
    }
}