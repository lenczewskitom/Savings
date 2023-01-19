package com.kodilla.savings.client;

import com.kodilla.savings.domain.dto.nbp.NbpResponseDto;
import com.kodilla.savings.domain.dto.nbp.RatesDto;
import com.kodilla.savings.domain.enums.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

@Component
@RequiredArgsConstructor
public class NbpApiClient {

    private final RestTemplate restTemplate;

    public RatesDto getExchangeRate(Currency currency) {
        URI uri = UriComponentsBuilder.fromHttpUrl("https://api.nbp.pl/api/exchangerates/rates/a/" + currency)
                .build()
                .encode()
                .toUri();
        NbpResponseDto nbpResponseDto = restTemplate.getForObject(uri, NbpResponseDto.class);
        return nbpResponseDto.getRates().get(0);
    }

}
