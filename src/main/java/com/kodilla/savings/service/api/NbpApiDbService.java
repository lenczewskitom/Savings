package com.kodilla.savings.service.api;

import com.kodilla.savings.client.NbpApiClient;
import com.kodilla.savings.domain.dto.nbp.RatesDto;
import com.kodilla.savings.domain.enums.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NbpApiDbService {

    private final NbpApiClient nbpApiClient;

    public RatesDto getRates(Currency currency) {

        return nbpApiClient.getExchangeRate(currency);
    }
}
