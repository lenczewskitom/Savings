package com.kodilla.savings.mapper;

import com.kodilla.savings.domain.CryptoOrder;
import com.kodilla.savings.domain.CurrencyOrder;
import com.kodilla.savings.domain.dto.CryptoOrderDto;
import com.kodilla.savings.domain.dto.CurrencyOrderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyOrderMapper {

    public CurrencyOrderDto mapToCurrencyOrderDto(final CurrencyOrder currencyOrder) {
        return new CurrencyOrderDto(
                currencyOrder.getCurrencyOrderId(),
                currencyOrder.getCurrencyOrderDate(),
                currencyOrder.getOrderCurrencyValue(),
                currencyOrder.getCurrencyCode(),
                currencyOrder.getCurrencyRate(),
                currencyOrder.getOperationType()
        );
    }

    public List<CurrencyOrderDto> mapToCurrencyOrderDtoList(final List<CurrencyOrder> currencyOrders) {
        return currencyOrders.stream()
                .map(this::mapToCurrencyOrderDto)
                .collect(Collectors.toList());
    }
}
