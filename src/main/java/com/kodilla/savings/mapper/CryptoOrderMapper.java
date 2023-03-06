package com.kodilla.savings.mapper;

import com.kodilla.savings.domain.CryptoOrder;
import com.kodilla.savings.domain.dto.CryptoOrderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CryptoOrderMapper {

    public CryptoOrderDto mapToCryptoOrderDto(final CryptoOrder cryptoOrder) {
        return new CryptoOrderDto(
                cryptoOrder.getCryptoOrderId(),
                cryptoOrder.getCryptoOrderDate(),
                cryptoOrder.getOrderCryptoValue(),
                cryptoOrder.getCryptoCode(),
                cryptoOrder.getCryptoRate(),
                cryptoOrder.getOperationType()
        );
    }

    public List<CryptoOrderDto> mapToCryptoOrderDtoList(final List<CryptoOrder> cryptoOrderList) {
        return cryptoOrderList.stream()
                .map(this::mapToCryptoOrderDto)
                .collect(Collectors.toList());
    }
}
