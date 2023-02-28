package com.kodilla.savings.repository;

import com.kodilla.savings.domain.CryptoRates;
import com.kodilla.savings.domain.CurrencyRates;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRatesRepository extends CrudRepository<CurrencyRates, Long> {

    @Query(nativeQuery = true)
    CurrencyRates getLastCurrencyRate(@Param("CODE")String currencyCode);

    @Query(nativeQuery = true)
    List<CurrencyRates> getLastCurrencyRatesList();

    @Override
    CurrencyRates save(CurrencyRates currencyRates);
}
