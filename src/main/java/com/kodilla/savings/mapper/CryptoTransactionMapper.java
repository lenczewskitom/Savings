package com.kodilla.savings.mapper;

import com.kodilla.savings.domain.CryptoTransaction;
import com.kodilla.savings.domain.dto.CryptoTransactionDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CryptoTransactionMapper {

    public CryptoTransactionDto mapToCryptoTransactionDto(final CryptoTransaction cryptoTransaction) {
        return new CryptoTransactionDto(
                cryptoTransaction.getTransactionId(),
                cryptoTransaction.getTransactionDate(),
                cryptoTransaction.getTransactionAccountValue(),
                cryptoTransaction.getCryptoCurrencyCode(),
                cryptoTransaction.getTransactionCryptoValue()
        );
    }

    public List<CryptoTransactionDto> mapToCryptoTransactionDtoList(final List<CryptoTransaction> cryptoTransactionList) {
        return cryptoTransactionList.stream()
                .map(this::mapToCryptoTransactionDto)
                .collect(Collectors.toList());
    }
}
