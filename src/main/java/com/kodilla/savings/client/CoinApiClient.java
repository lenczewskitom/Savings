package com.kodilla.savings.client;

import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.domain.dto.coinapi.CoinApiResponseDto;
import com.kodilla.savings.exception.TooManyRequestsException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class CoinApiClient {

    private final RestTemplate restTemplate;
    @Value("${coin.api.url}")
    private String url;

    @Value("${coin.api.apikey}")
    private String apikey;

    public CoinApiResponseDto getCryptocurrencyRates(CryptoCurrency cryptoCurrency) throws TooManyRequestsException {
        URI uri = UriComponentsBuilder.fromHttpUrl(url + cryptoCurrency + "/PLN")
                .queryParam("apikey", apikey)
                .build()
                .encode()
                .toUri();
        try {
            CoinApiResponseDto coinApiResponseDto = restTemplate.getForObject(uri, CoinApiResponseDto.class);
            return coinApiResponseDto;
        } catch (Exception e) {
            throw new TooManyRequestsException(e.getMessage());
        }
    }
}
