package com.kodilla.savings.client;

import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.domain.dto.coinapi.CoinApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class CoinApiClient {

    private final RestTemplate restTemplate;


    public CoinApiResponseDto getCryptocurrencyRates(CryptoCurrency cryptoCurrency) {
        URI uri = UriComponentsBuilder.fromHttpUrl("https://rest.coinapi.io/v1/exchangerate/" + cryptoCurrency + "/PLN")
                .queryParam("apikey", "B588AC37-CC25-4DF9-A0A6-6DDAB0C55668")
                .build()
                .encode()
                .toUri();
        CoinApiResponseDto coinApiResponseDto = restTemplate.getForObject(uri, CoinApiResponseDto.class);
        return coinApiResponseDto;
    }
}
