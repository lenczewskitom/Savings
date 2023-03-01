package com.kodilla.savings.service;

import com.kodilla.savings.domain.CurrencyOrder;
import com.kodilla.savings.repository.CurrencyOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyOrderDbService {

    private final CurrencyOrderRepository currencyOrderRepository;

    public void addCurrencyOrder(CurrencyOrder currencyOrder) {
        currencyOrderRepository.save(currencyOrder);
    }

    public List<CurrencyOrder> getBuyCurrencyOrders() {
        return currencyOrderRepository.getBuyCurrencyOrders();
    }

    public List<CurrencyOrder> getSellCurrencyOrders() {
        return currencyOrderRepository.getSellCurrencyOrders();
    }
}
