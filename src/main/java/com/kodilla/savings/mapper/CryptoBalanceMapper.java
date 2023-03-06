package com.kodilla.savings.mapper;

import com.kodilla.savings.domain.AccountBalance;
import com.kodilla.savings.domain.CryptoBalance;
import com.kodilla.savings.domain.dto.AccountBalanceDto;
import com.kodilla.savings.domain.dto.CryptoBalanceDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<CryptoBalanceDto> mapToCryptotBalanceDtoList(final List<CryptoBalance> cryptoBalanceList) {
        return cryptoBalanceList.stream()
                .map(this::mapToCryptoBalanceDto)
                .collect(Collectors.toList());
    }
}
