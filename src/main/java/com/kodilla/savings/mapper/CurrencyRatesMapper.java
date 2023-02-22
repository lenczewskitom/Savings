package com.kodilla.savings.mapper;

import com.kodilla.savings.domain.CurrencyRates;
import com.kodilla.savings.domain.dto.CurrencyRatesDto;
import org.springframework.stereotype.Service;

@Service
public class CurrencyRatesMapper {

    public CurrencyRates mapToCurrencyRates(final CurrencyRatesDto currencyRatesDto) {
        return new CurrencyRates(
                currencyRatesDto.getCurrencyRateId(),
                currencyRatesDto.getRate(),
                currencyRatesDto.getLastRate(),
                currencyRatesDto.getRateChange(),
                currencyRatesDto.getCurrencyCode()
        );
    }

    public CurrencyRatesDto mapToCurrencyRatesDto(final CurrencyRates currencyRates) {
        return new CurrencyRatesDto(
                currencyRates.getCurrencyRateId(),
                currencyRates.getRate(),
                currencyRates.getLastRate(),
                currencyRates.getRateChange(),
                currencyRates.getCurrencyCode()
        );
    }
}
