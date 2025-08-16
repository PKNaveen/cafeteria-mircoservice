package com.cafeteria.user_service.domain;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.Refill;
import io.github.bucket4j.distributed.proxy.ProxyManager;
import java.time.Duration;
import java.util.function.Supplier;
import org.springframework.stereotype.Service;

@Service
public class RateLimiter {

    private final ProxyManager<String> proxyManager;

    //    ProxyManager to store the bucket in redis

    public RateLimiter(ProxyManager<String> proxyManager) {
        this.proxyManager = proxyManager;
    }

    //    Bucket

    public Bucket resolveBucket(String key, long limitPerMinute) {
        Supplier<BucketConfiguration> configSupplier = getConfigSupplier(limitPerMinute);
        return proxyManager.builder().build(key, configSupplier);
    }

    //    Bucket Refil

    private Supplier<BucketConfiguration> getConfigSupplier(long limitPerMinute) {
        Refill refill = Refill.intervally(limitPerMinute, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(limitPerMinute, refill);
        return () -> BucketConfiguration.builder().addLimit(limit).build();
    }
}
