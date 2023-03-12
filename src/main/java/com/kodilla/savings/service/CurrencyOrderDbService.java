package com.kodilla.savings.service;

import com.kodilla.savings.domain.CryptoOrder;
import com.kodilla.savings.domain.CurrencyOrder;
import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.domain.enums.Currency;
import com.kodilla.savings.domain.enums.Order;
import com.kodilla.savings.exception.NotEnoughCurrencyException;
import com.kodilla.savings.exception.NotEnoughMoneyException;
import com.kodilla.savings.exception.notFound.CurrencyOrderNotFoundException;
import com.kodilla.savings.repository.CurrencyOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyOrderDbService {

    private final CurrencyOrderRepository currencyOrderRepository;
    private final AccountBalanceDbService accountBalanceDbService;
    private final CurrencyBalanceDbService currencyBalanceDbService;

    public void addCurrencyOrder(BigDecimal currencyValue, Currency currencyCode,
                                 BigDecimal currencyRate, Order operationType) throws NotEnoughMoneyException, NotEnoughCurrencyException {
        if (operationType == Order.BUY) {
            BigDecimal accountValue = currencyValue.multiply(currencyRate).add(getAllOrdersAccountValue());
            if (accountBalanceDbService.getAccountBalance().getBalance().compareTo(accountValue) < 0) {
                throw new NotEnoughMoneyException();
            } else {
                currencyOrderRepository.save(new CurrencyOrder(currencyValue, currencyCode, currencyRate, operationType));
            }
        } else if (operationType == Order.SELL) {
            if (currencyBalanceDbService.getCurrencyBalance(currencyCode).getBalance().compareTo(currencyValue) < 0) {
                throw new NotEnoughCurrencyException();
            } else {
                currencyOrderRepository.save(new CurrencyOrder(currencyValue, currencyCode, currencyRate, operationType));
            }
        }
    }

    public void deleteCurrencyOrder(Long id) throws CurrencyOrderNotFoundException {
        currencyOrderRepository.findById(id).orElseThrow(CurrencyOrderNotFoundException::new);
        currencyOrderRepository.deleteById(id);
    }

    public List<CurrencyOrder> getAllCurrencyOrders() {return currencyOrderRepository.findAll();}

    public BigDecimal getAllOrdersAccountValue() {
        BigDecimal sum = BigDecimal.ZERO;
        for (CurrencyOrder order: currencyOrderRepository.getBuyCurrencyOrders()) {
            sum = sum.add(order.getOrderCurrencyValue().multiply(order.getCurrencyRate()));
        }
        return sum;
    }

    public BigDecimal getAllOrdersCurrencyValue(Currency currencyCode) {
        BigDecimal sum = BigDecimal.ZERO;
        for (CurrencyOrder order: currencyOrderRepository.getSellCurrencyOrders().stream()
                .filter(order -> order.getCurrencyCode() == currencyCode)
                .collect(Collectors.toList())) {
            sum = sum.add(order.getOrderCurrencyValue());
        }
        return sum;
    }

    public List<CurrencyOrder> getBuyCurrencyOrders() {
        return currencyOrderRepository.getBuyCurrencyOrders();
    }

    public List<CurrencyOrder> getSellCurrencyOrders() {
        return currencyOrderRepository.getSellCurrencyOrders();
    }
}
