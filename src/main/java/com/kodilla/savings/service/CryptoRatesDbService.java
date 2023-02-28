package com.kodilla.savings.service;

import com.kodilla.savings.domain.CryptoBalance;
import com.kodilla.savings.domain.CryptoRates;
import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.repository.CryptoRatesRepository;
import com.kodilla.savings.service.api.CoinApiDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    public void addData() {
        CryptoRates btc = new CryptoRates(coinApiDbService.getCryptoRates(CryptoCurrency.BTC).getRate(), BigDecimal.ZERO, CryptoCurrency.BTC);
        CryptoRates etc = new CryptoRates(coinApiDbService.getCryptoRates(CryptoCurrency.ETC).getRate(), BigDecimal.ZERO, CryptoCurrency.ETC);
        CryptoRates sol = new CryptoRates(coinApiDbService.getCryptoRates(CryptoCurrency.SOL).getRate(), BigDecimal.ZERO, CryptoCurrency.SOL);
        CryptoRates ltc = new CryptoRates(coinApiDbService.getCryptoRates(CryptoCurrency.LTC).getRate(), BigDecimal.ZERO, CryptoCurrency.LTC);
        CryptoRates doge = new CryptoRates(coinApiDbService.getCryptoRates(CryptoCurrency.DOGE).getRate(), BigDecimal.ZERO, CryptoCurrency.DOGE);
        cryptoRatesRepository.save(btc);
        cryptoRatesRepository.save(etc);
        cryptoRatesRepository.save(sol);
        cryptoRatesRepository.save(ltc);
        cryptoRatesRepository.save(doge);
    }
}
