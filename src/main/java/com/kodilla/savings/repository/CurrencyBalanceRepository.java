package com.kodilla.savings.repository;

import com.kodilla.savings.domain.CurrencyBalance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyBalanceRepository extends CrudRepository<CurrencyBalance, Long> {
}
