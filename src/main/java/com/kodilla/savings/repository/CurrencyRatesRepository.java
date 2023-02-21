package com.kodilla.savings.repository;

import com.kodilla.savings.domain.CurrencyRates;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRatesRepository extends CrudRepository<CurrencyRates, Long> {
}
