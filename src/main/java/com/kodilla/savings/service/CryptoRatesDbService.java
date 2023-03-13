package com.kodilla.savings.service;

import com.kodilla.savings.domain.CryptoBalance;
import com.kodilla.savings.domain.CryptoRates;
import com.kodilla.savings.domain.CurrencyRates;
import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.domain.enums.Currency;
import com.kodilla.savings.exception.TooManyRequestsException;
import com.kodilla.savings.repository.CryptoRatesRepository;
import com.kodilla.savings.service.api.CoinApiDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CryptoRatesDbService {

    private final CryptoRatesRepository cryptoRatesRepository;
    private final CoinApiDbService coinApiDbService;

    public List<CryptoRates> getTopCryptoRates() {
        return cryptoRatesRepository.getLastCryptoRatesList();
    }

    //@Cacheable(cacheNames = "AllCryptoRates")
    public List<CryptoRates> getAllCryptoRates() throws TooManyRequestsException {
        List<CryptoRates> cryptoRatesList = new ArrayList<>();
        cryptoRatesList.add(new CryptoRates(coinApiDbService.getCryptoRates(CryptoCurrency.BTC).getRate().setScale(4, RoundingMode.CEILING), CryptoCurrency.BTC));
        cryptoRatesList.add(new CryptoRates(coinApiDbService.getCryptoRates(CryptoCurrency.ETC).getRate().setScale(4, RoundingMode.CEILING), CryptoCurrency.ETC));
        cryptoRatesList.add(new CryptoRates(coinApiDbService.getCryptoRates(CryptoCurrency.LTC).getRate().setScale(4, RoundingMode.CEILING), CryptoCurrency.LTC));
        cryptoRatesList.add(new CryptoRates(coinApiDbService.getCryptoRates(CryptoCurrency.SOL).getRate().setScale(4, RoundingMode.CEILING), CryptoCurrency.SOL));
        cryptoRatesList.add(new CryptoRates(coinApiDbService.getCryptoRates(CryptoCurrency.DOGE).getRate().setScale(4, RoundingMode.CEILING), CryptoCurrency.DOGE));
        return cryptoRatesList;
    }

    public void updateCryptoRates() throws TooManyRequestsException {
        BigDecimal btcLastRate = cryptoRatesRepository.getLastCryptoRate(CryptoCurrency.BTC.name()).getLastRate();
        BigDecimal btcRate = coinApiDbService.getCryptoRates(CryptoCurrency.BTC).getRate();
        CryptoRates btc = new CryptoRates(
                btcRate,
                ((btcRate.subtract(btcLastRate)).divide(btcLastRate, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))),
                CryptoCurrency.BTC
        );

        BigDecimal etcLastRate = cryptoRatesRepository.getLastCryptoRate(CryptoCurrency.ETC.name()).getLastRate();
        BigDecimal etcRate = coinApiDbService.getCryptoRates(CryptoCurrency.ETC).getRate();
        CryptoRates etc = new CryptoRates(
                etcRate,
                ((etcRate.subtract(etcLastRate)).divide(btcLastRate, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))),
                CryptoCurrency.ETC
        );

        BigDecimal ltcLastRate = cryptoRatesRepository.getLastCryptoRate(CryptoCurrency.LTC.name()).getLastRate();
        BigDecimal ltcRate = coinApiDbService.getCryptoRates(CryptoCurrency.LTC).getRate();
        CryptoRates ltc = new CryptoRates(
                ltcRate,
                ((ltcRate.subtract(ltcLastRate)).divide(ltcLastRate, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))),
                CryptoCurrency.LTC
        );

        BigDecimal solLastRate = cryptoRatesRepository.getLastCryptoRate(CryptoCurrency.SOL.name()).getLastRate();
        BigDecimal solRate = coinApiDbService.getCryptoRates(CryptoCurrency.SOL).getRate();
        CryptoRates sol = new CryptoRates(
                solRate,
                ((solRate.subtract(solLastRate)).divide(solLastRate, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))),
                CryptoCurrency.SOL
        );

        BigDecimal dogeLastRate = cryptoRatesRepository.getLastCryptoRate(CryptoCurrency.DOGE.name()).getLastRate();
        BigDecimal dogeRate = coinApiDbService.getCryptoRates(CryptoCurrency.DOGE).getRate();
        CryptoRates doge = new CryptoRates(
                dogeRate,
                ((dogeRate.subtract(dogeLastRate)).divide(dogeLastRate, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))),
                CryptoCurrency.DOGE
        );
        cryptoRatesRepository.save(btc);
        cryptoRatesRepository.save(etc);
        cryptoRatesRepository.save(ltc);
        cryptoRatesRepository.save(sol);
        cryptoRatesRepository.save(doge);
    }
}
