package com.kodilla.savings.service;

import com.kodilla.savings.domain.CryptoBalance;
import com.kodilla.savings.domain.CurrencyBalance;
import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.domain.enums.Currency;
import com.kodilla.savings.repository.CryptoBalanceRepository;
import com.kodilla.savings.service.api.CoinApiDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    public List<CryptoBalance> getAllCuryptoBalanceList() {
        List<CryptoBalance> cryptoBalanceList = new ArrayList<>();
        cryptoBalanceList.add(cryptoBalanceRepository.getCryptoBalance(CryptoCurrency.BTC.name()));
        cryptoBalanceList.add(cryptoBalanceRepository.getCryptoBalance(CryptoCurrency.ETC.name()));
        cryptoBalanceList.add(cryptoBalanceRepository.getCryptoBalance(CryptoCurrency.LTC.name()));
        cryptoBalanceList.add(cryptoBalanceRepository.getCryptoBalance(CryptoCurrency.SOL.name()));
        cryptoBalanceList.add(cryptoBalanceRepository.getCryptoBalance(CryptoCurrency.DOGE.name()));
        return cryptoBalanceList;
    }

    public void updateCryptoBalance(CryptoCurrency cryptoCurrencyCode, BigDecimal cryptocurrencyValue) {
        BigDecimal newBalance = cryptoBalanceRepository.getCryptoBalance(cryptoCurrencyCode.name()).getBalance().add(cryptocurrencyValue);
        CryptoBalance cryptoBalance = new CryptoBalance(newBalance, cryptoCurrencyCode);
        cryptoBalanceRepository.save(cryptoBalance);
    }

    public BigDecimal getAllSavings() {
        BigDecimal btc = cryptoBalanceRepository.getCryptoBalance(CryptoCurrency.BTC.name()).getBalance()
                .multiply(coinApiDbService.getCryptoRates(CryptoCurrency.BTC).getRate());
        BigDecimal etc = cryptoBalanceRepository.getCryptoBalance(CryptoCurrency.ETC.name()).getBalance()
                .multiply(coinApiDbService.getCryptoRates(CryptoCurrency.ETC).getRate());
        BigDecimal ltc = cryptoBalanceRepository.getCryptoBalance(CryptoCurrency.LTC.name()).getBalance()
                .multiply(coinApiDbService.getCryptoRates(CryptoCurrency.LTC).getRate());
        BigDecimal sol = cryptoBalanceRepository.getCryptoBalance(CryptoCurrency.SOL.name()).getBalance()
                .multiply(coinApiDbService.getCryptoRates(CryptoCurrency.SOL).getRate());
        BigDecimal doge = cryptoBalanceRepository.getCryptoBalance(CryptoCurrency.DOGE.name()).getBalance()
                .multiply(coinApiDbService.getCryptoRates(CryptoCurrency.DOGE).getRate());
        return btc.add(etc).add(ltc).add(sol).add(doge);
    }
}
