package com.kodilla.savings.mapper;

import com.kodilla.savings.domain.CryptoBalance;
import com.kodilla.savings.domain.dto.CryptoBalanceDto;
import org.springframework.stereotype.Service;

@Service
public class CryptoBalanceMapper {

    public CryptoBalance mapToCryptoBalance(final CryptoBalanceDto cryptoBalanceDto) {
        return new CryptoBalance(
                cryptoBalanceDto.getCryptoBalanceId(),
                cryptoBalanceDto.getBalance(),
                cryptoBalanceDto.getCryptocurrencyCode()
        );
    }

    public CryptoBalanceDto mapToCryptoBalanceDto(final CryptoBalance cryptoBalance) {
        return new CryptoBalanceDto(
                cryptoBalance.getCryptoBalanceId(),
                cryptoBalance.getBalance(),
                cryptoBalance.getCryptocurrencyCode()
        );
    }
}
