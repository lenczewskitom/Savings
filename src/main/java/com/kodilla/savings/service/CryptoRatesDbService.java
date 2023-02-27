package com.kodilla.savings.service;

import com.kodilla.savings.domain.CryptoRates;
import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.repository.CryptoRatesRepository;
import com.kodilla.savings.service.api.CoinApiDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public void updateCryptoRates() {
        BigDecimal btcLastRate = cryptoRatesRepository.getLastCryptoRate(CryptoCurrency.BTC.name()).getLastRate();
        BigDecimal btcRate = coinApiDbService.getCryptoRates(CryptoCurrency.BTC).getRate();
        CryptoRates btc = new CryptoRates(
                btcLastRate,
                ((btcRate.subtract(btcLastRate)).divide(btcLastRate).multiply(BigDecimal.valueOf(100))),
                CryptoCurrency.BTC
        );

        BigDecimal etcLastRate = cryptoRatesRepository.getLastCryptoRate(CryptoCurrency.ETC.name()).getLastRate();
        BigDecimal etcRate = coinApiDbService.getCryptoRates(CryptoCurrency.ETC).getRate();
        CryptoRates etc = new CryptoRates(
                etcLastRate,
                ((etcRate.subtract(etcLastRate)).divide(btcLastRate).multiply(BigDecimal.valueOf(100))),
                CryptoCurrency.ETC
        );

        BigDecimal ltcLastRate = cryptoRatesRepository.getLastCryptoRate(CryptoCurrency.LTC.name()).getLastRate();
        BigDecimal ltcRate = coinApiDbService.getCryptoRates(CryptoCurrency.LTC).getRate();
        CryptoRates ltc = new CryptoRates(
                ltcLastRate,
                ((ltcRate.subtract(ltcLastRate)).divide(ltcLastRate).multiply(BigDecimal.valueOf(100))),
                CryptoCurrency.LTC
        );

        BigDecimal solLastRate = cryptoRatesRepository.getLastCryptoRate(CryptoCurrency.SOL.name()).getLastRate();
        BigDecimal solRate = coinApiDbService.getCryptoRates(CryptoCurrency.SOL).getRate();
        CryptoRates sol = new CryptoRates(
                solLastRate,
                ((solRate.subtract(solLastRate)).divide(solLastRate).multiply(BigDecimal.valueOf(100))),
                CryptoCurrency.SOL
        );

        BigDecimal dogeLastRate = cryptoRatesRepository.getLastCryptoRate(CryptoCurrency.DOGE.name()).getLastRate();
        BigDecimal dogeRate = coinApiDbService.getCryptoRates(CryptoCurrency.DOGE).getRate();
        CryptoRates doge = new CryptoRates(
                dogeLastRate,
                ((dogeRate.subtract(dogeLastRate)).divide(dogeLastRate).multiply(BigDecimal.valueOf(100))),
                CryptoCurrency.DOGE
        );
        cryptoRatesRepository.save(btc);
        cryptoRatesRepository.save(etc);
        cryptoRatesRepository.save(ltc);
        cryptoRatesRepository.save(sol);
        cryptoRatesRepository.save(doge);
    }
}
