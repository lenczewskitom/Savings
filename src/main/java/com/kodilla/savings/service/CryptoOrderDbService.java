package com.kodilla.savings.service;

import com.kodilla.savings.domain.CryptoOrder;
import com.kodilla.savings.domain.enums.CryptoCurrency;
import com.kodilla.savings.domain.enums.Order;
import com.kodilla.savings.exception.NotEnoughCryptoException;
import com.kodilla.savings.exception.NotEnoughMoneyException;
import com.kodilla.savings.exception.notFound.CryptoOrderNotFoundException;
import com.kodilla.savings.repository.CryptoOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CryptoOrderDbService {

    private final CryptoOrderRepository cryptoOrderRepository;
    private final AccountBalanceDbService accountBalanceDbService;
    private final CryptoBalanceDbService cryptoBalanceDbService;

    public void addCryptoOrder(BigDecimal cryptoValue, CryptoCurrency cryptoCode,
                               BigDecimal cryptoRate, Order operationType) throws NotEnoughCryptoException, NotEnoughMoneyException {
        if (operationType == Order.BUY) {
            BigDecimal accountValue = cryptoValue.multiply(cryptoRate).add(getAllOrdersAccountValue());
            if (accountBalanceDbService.getAccountBalance().getBalance().compareTo(accountValue) < 0) {
                throw new NotEnoughMoneyException();
            } else {
                cryptoOrderRepository.save(new CryptoOrder(cryptoValue, cryptoCode, cryptoRate, operationType));
            }
        } else if (operationType == Order.SELL) {
            if (cryptoBalanceDbService.getCryptoBalance(cryptoCode).getBalance().compareTo(cryptoValue) < 0) {
                throw new NotEnoughCryptoException();
            } else {
                cryptoOrderRepository.save(new CryptoOrder(cryptoValue, cryptoCode, cryptoRate, operationType));
            }
        }
    }

    public void deleteCryptoOrder(Long id) throws CryptoOrderNotFoundException {
        cryptoOrderRepository.findById(id).orElseThrow(CryptoOrderNotFoundException::new);
        cryptoOrderRepository.deleteById(id);
    }

    public List<CryptoOrder> getAllCryptoOrders() {return cryptoOrderRepository.findAll();}

    public BigDecimal getAllOrdersAccountValue() {
        BigDecimal sum = BigDecimal.ZERO;
        for (CryptoOrder order: cryptoOrderRepository.getBuyCryptoOrders()) {
            sum = sum.add(order.getOrderCryptoValue().multiply(order.getCryptoRate()));
        }
        return sum;
    }

    public BigDecimal getAllOrdersCryptoValue(CryptoCurrency cryptoCode) {
        BigDecimal sum = BigDecimal.ZERO;
        for (CryptoOrder order: cryptoOrderRepository.getSellCryptoOrders().stream()
                .filter(order -> order.getCryptoCode() == cryptoCode)
                .collect(Collectors.toList())) {
            sum = sum.add(order.getOrderCryptoValue());
        }
        return sum;
    }

    public List<CryptoOrder> getBuyCryptoOrders() {
        return cryptoOrderRepository.getBuyCryptoOrders();
    }

    public List<CryptoOrder> getSellCryptoOrders() {
        return cryptoOrderRepository.getSellCryptoOrders();
    }
}
