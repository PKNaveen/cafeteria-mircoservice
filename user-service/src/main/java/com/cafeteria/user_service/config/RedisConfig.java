package com.cafeteria.user_service.config;

import io.github.bucket4j.distributed.proxy.ProxyManager;
import io.github.bucket4j.grid.jcache.JCacheProxyManager;
import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import org.redisson.config.Config;
import org.redisson.jcache.configuration.RedissonConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

    @Value("${redis.url}")
    private String redisUrl; // e.g., redis://127.0.0.1:6379

    @Bean
    public Config redissonConfig() {
        Config config = new Config();
        config.useSingleServer().setAddress(redisUrl);
        return config;
    }

    @Bean
    public CacheManager cacheManager(Config redissonConfig) {
        // ✅ Use Redisson's CachingProvider, not the default
        CachingProvider provider = Caching.getCachingProvider("org.redisson.jcache.JCachingProvider");
        CacheManager manager = provider.getCacheManager();

        // Create the cache backed by Redis
        manager.createCache("bucket4j-cache", RedissonConfiguration.fromConfig(redissonConfig));
        return manager;
    }

    @Bean
    public ProxyManager<String> proxyManager(CacheManager cacheManager) {
        Cache<String, byte[]> cache = cacheManager.getCache("bucket4j-cache");
        return new JCacheProxyManager<>(cache);
    }
}
