package com.kodilla.savings.mapper;

import com.kodilla.savings.domain.CurrencyBalance;
import com.kodilla.savings.domain.CurrencyRates;
import com.kodilla.savings.domain.dto.CurrencyBalanceDto;
import com.kodilla.savings.domain.dto.CurrencyRatesDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyRatesMapper {

    public CurrencyRatesDto mapToCurrencyRatesDto(final CurrencyRates currencyRates) {
        return new CurrencyRatesDto(
                currencyRates.getCurrencyRateId(),
                currencyRates.getLastRate(),
                currencyRates.getRateChange(),
                currencyRates.getCurrencyCode()
        );
    }

    public List<CurrencyRatesDto> mapToCurrencyRatesDtoList(final List<CurrencyRates> currencyRates) {
        return currencyRates.stream()
                .map(this::mapToCurrencyRatesDto)
                .collect(Collectors.toList());
    }
}
