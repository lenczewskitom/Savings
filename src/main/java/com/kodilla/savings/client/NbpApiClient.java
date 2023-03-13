package com.kodilla.savings.client;

import com.kodilla.savings.domain.dto.nbp.NbpResponseDto;
import com.kodilla.savings.domain.dto.nbp.RatesDto;
import com.kodilla.savings.domain.enums.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

@Component
@RequiredArgsConstructor
public class NbpApiClient {

    private final RestTemplate restTemplate;
    @Value("${nbp.api.url}")
    private String url;

    public RatesDto getExchangeRate(Currency currency) {
        URI uri = UriComponentsBuilder.fromHttpUrl(url + currency)
                .build()
                .encode()
                .toUri();
        NbpResponseDto nbpResponseDto = restTemplate.getForObject(uri, NbpResponseDto.class);
        return nbpResponseDto.getRates().get(0);
    }

}
