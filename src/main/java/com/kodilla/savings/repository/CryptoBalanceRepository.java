package com.kodilla.savings.repository;

import com.kodilla.savings.domain.CryptoBalance;
import com.kodilla.savings.domain.enums.CryptoCurrency;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoBalanceRepository extends CrudRepository<CryptoBalance, Long> {

    @Query(nativeQuery = true)
    CryptoBalance getCryptoBalance(@Param("CODE")String cryptoCurrencyCode);

    @Override
    CryptoBalance save(CryptoBalance cryptoBalance);
}
