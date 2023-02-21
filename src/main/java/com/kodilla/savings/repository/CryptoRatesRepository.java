package com.kodilla.savings.repository;

import com.kodilla.savings.domain.CryptoRates;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoRatesRepository extends CrudRepository<CryptoRates, Long> {
}
