package io.ganguo.chat.route.config;

import io.ganguo.chat.route.bean.Constants;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tony on 2/22/15.
 */
@Configuration
@EnableCaching
public class CachingConfiguration extends CachingConfigurerSupport {

    @Bean
    @Override
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        List<Cache> caches = new ArrayList<Cache>();
        // Ehcache/Redis
        caches.add(new ConcurrentMapCache(Constants.CACHE_USER_ONLINE, false));
        caches.add(new ConcurrentMapCache(Constants.CACHE_SERVERS, false));
        cacheManager.setCaches(caches);

        return cacheManager;
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

}
