package com.kodilla.savings.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ClearCacheScheduler {

    @Caching(evict = {
            @CacheEvict(cacheNames = "AllCryptoRates", allEntries = true),
            @CacheEvict(cacheNames = "CryptoRate", allEntries = true)
    })
    @Scheduled(cron = "0 */30 * * * *")
    public void emptyAllCryptoCache() {
        log.info("CryptoRates cache cleared");
    }
}
