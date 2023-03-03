package com.kodilla.savings.repository;

import com.kodilla.savings.domain.CurrencyOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyOrderRepository extends CrudRepository<CurrencyOrder, Long> {

    CurrencyOrder save(CurrencyOrder currencyOrder);

    @Query
    List<CurrencyOrder> getBuyCurrencyOrders();

    @Query
    List<CurrencyOrder> getSellCurrencyOrders();

    List<CurrencyOrder> findAll();
}
