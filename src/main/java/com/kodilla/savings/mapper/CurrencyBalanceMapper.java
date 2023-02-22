package com.kodilla.savings.mapper;

import com.kodilla.savings.domain.CurrencyBalance;
import com.kodilla.savings.domain.dto.CurrencyBalanceDto;
import org.springframework.stereotype.Service;

@Service
public class CurrencyBalanceMapper {

    public CurrencyBalance mapToCurrencyBalance(final CurrencyBalanceDto currencyBalanceDto) {
        return new CurrencyBalance(
                currencyBalanceDto.getCurrencyBalanceId(),
                currencyBalanceDto.getBalance(),
                currencyBalanceDto.getCurrencyCode()
        );
    }

    public CurrencyBalanceDto mapToCurrencyBalanceDto(final CurrencyBalance currencyBalance) {
        return new CurrencyBalanceDto(
                currencyBalance.getCurrencyBalanceId(),
                currencyBalance.getBalance(),
                currencyBalance.getCurrencyCode()
        );
    }
}
