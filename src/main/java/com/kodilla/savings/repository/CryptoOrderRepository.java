package com.kodilla.savings.repository;

import com.kodilla.savings.domain.CryptoOrder;
import com.kodilla.savings.domain.CurrencyOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CryptoOrderRepository extends CrudRepository<CryptoOrder, Long> {

    CryptoOrder save(CryptoOrder cryptoOrder);

    @Query
    List<CryptoOrder> getBuyCryptoOrders();

    @Query
    List<CryptoOrder> getSellCryptoOrders();

    List<CryptoOrder> findAll();
}
