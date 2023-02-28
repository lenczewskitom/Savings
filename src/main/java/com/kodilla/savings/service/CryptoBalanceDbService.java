package com.kodilla.savings.service;

import com.kodilla.savings.domain.CryptoBalance;
import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.domain.enums.Currency;
import com.kodilla.savings.repository.CryptoBalanceRepository;
import com.kodilla.savings.service.api.CoinApiDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CryptoBalanceDbService {

    private final CryptoBalanceRepository cryptoBalanceRepository;
    private final CoinApiDbService coinApiDbService;

    public void addData() {
        CryptoBalance btc = new CryptoBalance(BigDecimal.ZERO, CryptoCurrency.BTC);
        CryptoBalance etc = new CryptoBalance(BigDecimal.ZERO, CryptoCurrency.ETC);
        CryptoBalance sol = new CryptoBalance(BigDecimal.ZERO, CryptoCurrency.SOL);
        CryptoBalance ltc = new CryptoBalance(BigDecimal.ZERO, CryptoCurrency.LTC);
        CryptoBalance doge = new CryptoBalance(BigDecimal.ZERO, CryptoCurrency.DOGE);
        cryptoBalanceRepository.save(btc);
        cryptoBalanceRepository.save(etc);
        cryptoBalanceRepository.save(sol);
        cryptoBalanceRepository.save(ltc);
        cryptoBalanceRepository.save(doge);

    }

    public CryptoBalance getCryptoBalance(CryptoCurrency cryptoCurrencyCode) {
        return cryptoBalanceRepository.getCryptoBalance(cryptoCurrencyCode.name());
    }

    public void updateCryptoBalance(CryptoCurrency cryptoCurrencyCode, BigDecimal cryptocurrencyValue) {
        BigDecimal newBalance = cryptoBalanceRepository.getCryptoBalance(cryptoCurrencyCode.name()).getBalance().add(cryptocurrencyValue);
        CryptoBalance cryptoBalance = new CryptoBalance(newBalance, cryptoCurrencyCode);
        cryptoBalanceRepository.save(cryptoBalance);
    }

    public BigDecimal getAllSavings() {
        BigDecimal btc = cryptoBalanceRepository.getCryptoBalance(Currency.EUR.name()).getBalance()
                .multiply(coinApiDbService.getCryptoRates(CryptoCurrency.BTC).getRate());
        BigDecimal etc = cryptoBalanceRepository.getCryptoBalance(Currency.USD.name()).getBalance()
                .multiply(coinApiDbService.getCryptoRates(CryptoCurrency.ETC).getRate());
        BigDecimal ltc = cryptoBalanceRepository.getCryptoBalance(Currency.GBP.name()).getBalance()
                .multiply(coinApiDbService.getCryptoRates(CryptoCurrency.LTC).getRate());
        BigDecimal sol = cryptoBalanceRepository.getCryptoBalance(Currency.CHF.name()).getBalance()
                .multiply(coinApiDbService.getCryptoRates(CryptoCurrency.SOL).getRate());
        BigDecimal doge = cryptoBalanceRepository.getCryptoBalance(Currency.CNY.name()).getBalance()
                .multiply(coinApiDbService.getCryptoRates(CryptoCurrency.DOGE).getRate());
        return btc.add(etc).add(ltc).add(sol).add(doge);
    }
}
