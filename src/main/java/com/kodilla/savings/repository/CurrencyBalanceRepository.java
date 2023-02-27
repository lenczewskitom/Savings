package com.kodilla.savings.repository;

import com.kodilla.savings.domain.CurrencyBalance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyBalanceRepository extends CrudRepository<CurrencyBalance, Long> {

    @Query(nativeQuery = true)
    CurrencyBalance getCurrencyBalance(@Param("CODE")String currencyCode);

    @Override
    CurrencyBalance save(CurrencyBalance currencyBalance);
}
