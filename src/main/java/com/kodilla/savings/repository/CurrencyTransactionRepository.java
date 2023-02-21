package com.kodilla.savings.repository;

import com.kodilla.savings.domain.CurrencyTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyTransactionRepository extends CrudRepository<CurrencyTransaction, Long> {
}
