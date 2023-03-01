package com.kodilla.savings.service;

import com.kodilla.savings.domain.CryptoOrder;
import com.kodilla.savings.repository.CryptoOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CryptoOrderDbService {

    private final CryptoOrderRepository cryptoOrderRepository;

    public void addCryptoOrder(CryptoOrder cryptoOrder) {
        cryptoOrderRepository.save(cryptoOrder);
    }

    public List<CryptoOrder> getBuyCryptoOrders() {
        return cryptoOrderRepository.getBuyCryptoOrders();
    }

    public List<CryptoOrder> getSellCryptoOrders() {
        return cryptoOrderRepository.getSellCryptoOrders();
    }
}
