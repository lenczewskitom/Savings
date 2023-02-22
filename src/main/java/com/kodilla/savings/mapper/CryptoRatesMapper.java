package com.kodilla.savings.mapper;

import com.kodilla.savings.domain.CryptoRates;
import com.kodilla.savings.domain.dto.CryptoRatesDto;
import org.springframework.stereotype.Service;

@Service
public class CryptoRatesMapper {

    public CryptoRates mapToCryptoRates(final CryptoRatesDto cryptoRatesDto) {
        return new CryptoRates(
                cryptoRatesDto.getCryptoRateId(),
                cryptoRatesDto.getRate(),
                cryptoRatesDto.getLastRate(),
                cryptoRatesDto.getRateChange(),
                cryptoRatesDto.getCryptocurrencyCode()
        );
    }

    public CryptoRatesDto mapToCryptoRatesDto(final CryptoRates cryptoRates) {
        return new CryptoRatesDto(
                cryptoRates.getCryptoRateId(),
                cryptoRates.getLastRate(),
                cryptoRates.getLastRate(),
                cryptoRates.getRateChange(),
                cryptoRates.getCryptocurrencyCode()
        );
    }
}
