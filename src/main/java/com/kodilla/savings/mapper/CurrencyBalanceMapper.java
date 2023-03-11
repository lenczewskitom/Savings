package com.kodilla.savings.mapper;

import com.kodilla.savings.domain.CryptoBalance;
import com.kodilla.savings.domain.CurrencyBalance;
import com.kodilla.savings.domain.dto.CryptoBalanceDto;
import com.kodilla.savings.domain.dto.CurrencyBalanceDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyBalanceMapper {

    public CurrencyBalanceDto mapToCurrencyBalanceDto(final CurrencyBalance currencyBalance) {
        return new CurrencyBalanceDto(
                currencyBalance.getCurrencyBalanceId(),
                currencyBalance.getBalance(),
                currencyBalance.getCurrencyCode()
        );
    }

    public List<CurrencyBalanceDto> mapToCurrencyBalanceDtoList(final List<CurrencyBalance> currencyBalanceList) {
        return currencyBalanceList.stream()
                .map(this::mapToCurrencyBalanceDto)
                .collect(Collectors.toList());
    }
}
