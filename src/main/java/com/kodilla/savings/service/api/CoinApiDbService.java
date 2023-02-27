package com.kodilla.savings.service.api;

import com.kodilla.savings.client.CoinApiClient;
import com.kodilla.savings.domain.dto.coinapi.CoinApiResponseDto;
import com.kodilla.savings.domain.enums.CryptoCurrency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoinApiDbService {

    private final CoinApiClient coinApiResponseDto;

    public CoinApiResponseDto getCryptoRates(CryptoCurrency cryptoCurrencyCode) {
        return coinApiResponseDto.getCryptocurrencyRates(cryptoCurrencyCode);
    }
}
