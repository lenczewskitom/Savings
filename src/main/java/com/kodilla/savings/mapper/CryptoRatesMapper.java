package com.kodilla.savings.mapper;

import com.kodilla.savings.domain.CryptoBalance;
import com.kodilla.savings.domain.CryptoRates;
import com.kodilla.savings.domain.dto.CryptoBalanceDto;
import com.kodilla.savings.domain.dto.CryptoRatesDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CryptoRatesMapper {

    public CryptoRatesDto mapToCryptoRatesDto(final CryptoRates cryptoRates) {
        return new CryptoRatesDto(
                cryptoRates.getCryptoRateId(),
                cryptoRates.getLastRate(),
                cryptoRates.getRateChange(),
                cryptoRates.getCryptocurrencyCode()
        );
    }

    public List<CryptoRatesDto> mapToCryptotRatesDtoList(final List<CryptoRates> cryptoRatesList) {
        return cryptoRatesList.stream()
                .map(this::mapToCryptoRatesDto)
                .collect(Collectors.toList());
    }
}
