package com.kodilla.savings.scheduler;

import com.kodilla.savings.domain.CryptoRates;
import com.kodilla.savings.service.CryptoRatesDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TopRatesScheduler {

    private final CryptoRatesDbService cryptoRatesDbService;

    @Scheduled(cron = "0 0 6 * * *")
    public void getTopCryptoRates() {
        cryptoRatesDbService.updateCryptoRates();
    }
}
