package com.kodilla.savings.mapper;

import com.kodilla.savings.domain.CurrencyTransaction;
import com.kodilla.savings.domain.dto.CurrencyTransactionDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyTransactionMapper {

    public CurrencyTransaction mapToCurrencyTransaction(final CurrencyTransactionDto currencyTransactionDto) {
        return new CurrencyTransaction(
                currencyTransactionDto.getTransactionId(),
                currencyTransactionDto.getTransactionDate(),
                currencyTransactionDto.getTransactionAccountValue(),
                currencyTransactionDto.getCurrencyCode(),
                currencyTransactionDto.getTransactionCurrencyValue()
        );
    }

    public CurrencyTransactionDto mapToCurrencyTransactionDto(final CurrencyTransaction currencyTransaction) {
        return new CurrencyTransactionDto(
                currencyTransaction.getTransactionId(),
                currencyTransaction.getTransactionDate(),
                currencyTransaction.getTransactionAccountValue(),
                currencyTransaction.getCurrencyCode(),
                currencyTransaction.getTransactionCurrencyValue()
        );
    }

    public List<CurrencyTransactionDto> mapToCurrencyTransactionDtoList(final List<CurrencyTransaction> currencyTransactionList) {
        return currencyTransactionList.stream()
                .map(this::mapToCurrencyTransactionDto)
                .collect(Collectors.toList());
    }
}
