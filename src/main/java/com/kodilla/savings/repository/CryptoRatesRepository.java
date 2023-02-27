package com.kodilla.savings.repository;

import com.kodilla.savings.domain.CryptoRates;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CryptoRatesRepository extends CrudRepository<CryptoRates, Long> {

    @Query(nativeQuery = true)
    CryptoRates getLastCryptoRate(@Param("CODE")String cryptoCurrencyCode);

    @Query
    List<CryptoRates> getLastCryptoRatesList();

    @Override
    CryptoRates save(CryptoRates cryptoRates);
}
