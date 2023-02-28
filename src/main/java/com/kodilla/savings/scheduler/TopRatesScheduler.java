package com.kodilla.savings.scheduler;

import com.kodilla.savings.service.CryptoRatesDbService;
import com.kodilla.savings.service.CurrencyRatesDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class TopRatesScheduler {

    private final CryptoRatesDbService cryptoRatesDbService;
    private final CurrencyRatesDbService currencyRatesDbService;

    @Scheduled(cron = "0 0 6 * * *")
    public void getTopCryptoRates() {
        cryptoRatesDbService.updateCryptoRates();
    }

    @Scheduled(cron = "0 0 6 * * *")
    public void getTopCurrencyRates() {
        currencyRatesDbService.updateCurrencyRates();
    }
}
