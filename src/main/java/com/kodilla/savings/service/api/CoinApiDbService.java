package com.kodilla.savings.service.api;

import com.kodilla.savings.client.CoinApiClient;
import com.kodilla.savings.domain.dto.coinapi.CoinApiResponseDto;
import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.exception.TooManyRequestsException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoinApiDbService {

    private final CoinApiClient coinApiResponseDto;

    //@Cacheable(cacheNames = "CryptoRate", key = "#cryptoCurrencyCode")
    public CoinApiResponseDto getCryptoRates(CryptoCurrency cryptoCurrencyCode) throws TooManyRequestsException {
        return coinApiResponseDto.getCryptocurrencyRates(cryptoCurrencyCode);
    }
}
