package com.kodilla.savings.service;

import com.kodilla.savings.domain.CryptoBalance;
import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.repository.CryptoBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CryptoBalanceDbService {

    private final CryptoBalanceRepository cryptoBalanceRepository;

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
}
