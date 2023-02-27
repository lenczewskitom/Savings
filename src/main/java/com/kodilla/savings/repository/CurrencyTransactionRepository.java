package com.kodilla.savings.repository;

import com.kodilla.savings.domain.CurrencyTransaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyTransactionRepository extends CrudRepository<CurrencyTransaction, Long> {

    @Query
    List<CurrencyTransaction> reverseCurrencyTransactionsList();

    @Override
    CurrencyTransaction save(CurrencyTransaction currencyTransaction);
}
