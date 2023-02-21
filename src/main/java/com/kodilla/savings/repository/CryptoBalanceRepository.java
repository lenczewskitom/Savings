package com.kodilla.savings.repository;

import com.kodilla.savings.domain.CryptoBalance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoBalanceRepository extends CrudRepository<CryptoBalance, Long> {
}
