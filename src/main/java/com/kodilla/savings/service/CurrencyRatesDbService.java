package com.kodilla.savings.service;

import com.kodilla.savings.domain.CurrencyRates;
import com.kodilla.savings.domain.enums.Currency;
import com.kodilla.savings.repository.CurrencyRatesRepository;
import com.kodilla.savings.service.api.NbpApiDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyRatesDbService {

    private final CurrencyRatesRepository currencyRatesRepository;

    private final NbpApiDbService nbpApiDbService;

    public List<CurrencyRates> getTopCurrencyRates() {
        return currencyRatesRepository.getLastCurrencyRatesList();
    }

    public List<CurrencyRates> getAllCurrencyRates() {
        List<CurrencyRates> currencyRatesList = new ArrayList<>();
        currencyRatesList.add(new CurrencyRates(nbpApiDbService.getRates(Currency.EUR).getRate(), Currency.EUR));
        currencyRatesList.add(new CurrencyRates(nbpApiDbService.getRates(Currency.USD).getRate(), Currency.USD));
        currencyRatesList.add(new CurrencyRates(nbpApiDbService.getRates(Currency.GBP).getRate(), Currency.GBP));
        currencyRatesList.add(new CurrencyRates(nbpApiDbService.getRates(Currency.CHF).getRate(), Currency.CHF));
        currencyRatesList.add(new CurrencyRates(nbpApiDbService.getRates(Currency.CNY).getRate(), Currency.CNY));
        return currencyRatesList;
    }

    public void updateCurrencyRates() {

        BigDecimal eurLastRate = currencyRatesRepository.getLastCurrencyRate(Currency.EUR.name()).getLastRate();
        BigDecimal eurRate = nbpApiDbService.getRates(Currency.EUR).getRate();
        CurrencyRates eur = new CurrencyRates(
                eurRate,
                ((eurRate.subtract(eurLastRate)).divide(eurLastRate, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))),
                Currency.EUR
        );

        BigDecimal usdLastRate = currencyRatesRepository.getLastCurrencyRate(Currency.USD.name()).getLastRate();
        BigDecimal usdRate = nbpApiDbService.getRates(Currency.USD).getRate();
        CurrencyRates usd = new CurrencyRates(
                usdRate,
                ((usdRate.subtract(usdLastRate)).divide(usdLastRate, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))),
                Currency.USD
        );

        BigDecimal gbpLastRate = currencyRatesRepository.getLastCurrencyRate(Currency.GBP.name()).getLastRate();
        BigDecimal gbpRate = nbpApiDbService.getRates(Currency.GBP).getRate();
        CurrencyRates gbp = new CurrencyRates(
                gbpRate,
                ((gbpRate.subtract(gbpLastRate)).divide(gbpLastRate, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))),
                Currency.GBP
        );

        BigDecimal chfLastRate = currencyRatesRepository.getLastCurrencyRate(Currency.CHF.name()).getLastRate();
        BigDecimal chfRate = nbpApiDbService.getRates(Currency.CHF).getRate();
        CurrencyRates chf = new CurrencyRates(
                chfRate,
                ((chfRate.subtract(chfLastRate)).divide(chfLastRate, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))),
                Currency.CHF
        );

        BigDecimal cnyLastRate = currencyRatesRepository.getLastCurrencyRate(Currency.CNY.name()).getLastRate();
        BigDecimal cnyRate = nbpApiDbService.getRates(Currency.CNY).getRate();
        CurrencyRates cny = new CurrencyRates(
                cnyRate,
                ((cnyRate.subtract(cnyLastRate)).divide(cnyLastRate, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))),
                Currency.CNY
        );

        currencyRatesRepository.save(eur);
        currencyRatesRepository.save(usd);
        currencyRatesRepository.save(gbp);
        currencyRatesRepository.save(chf);
        currencyRatesRepository.save(cny);
    }
}
