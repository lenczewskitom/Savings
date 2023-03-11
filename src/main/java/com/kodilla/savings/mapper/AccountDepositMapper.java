package com.kodilla.savings.mapper;

import com.kodilla.savings.domain.AccountDeposit;
import com.kodilla.savings.domain.dto.AccountDepositDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountDepositMapper {

    public AccountDepositDto mapToAccountDepositDto(final AccountDeposit accountDeposit) {
        return new AccountDepositDto(
                accountDeposit.getDepositId(),
                accountDeposit.getDepositDate(),
                accountDeposit.getDepositValue(),
                accountDeposit.getDepositType()
        );
    }
    public List<AccountDepositDto> mapToAccountDepositDtoList(final List<AccountDeposit> accountDepositList) {
        return accountDepositList.stream()
                .map(this::mapToAccountDepositDto)
                .collect(Collectors.toList());
    }
}
