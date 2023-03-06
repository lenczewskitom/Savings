package com.kodilla.savings.scheduler;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClearCacheScheduler {

    @CacheEvict(cacheNames = "CryptoRates", allEntries = true)
    @Scheduled(cron = "0 */20 * * * *")
    public void emptyAllCurrencyTransactionsCache() {
        System.out.println("CryptoRates cache cleared");
    }
}
