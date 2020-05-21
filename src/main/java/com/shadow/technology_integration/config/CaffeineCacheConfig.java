package com.shadow.technology_integration.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 类描述：缓存配置管理类
 *
 * @author shadow
 * @date 2020/5/5
 */
@Configuration
@EnableCaching
@Slf4j
public class CaffeineCacheConfig {

    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        // 缓存集合
        ArrayList<CaffeineCache> caches = new ArrayList<>();
        // 对缓存key属性做设置
        caches.add(new CaffeineCache("users-cache",
                Caffeine.newBuilder()
                        // 指定Key下的最大缓存数据量
                        .maximumSize(1000)
                        // 最后一次访问之后 120秒 过期
                        .expireAfterAccess(120, TimeUnit.SECONDS)
                        .build()));
        caches.add(new CaffeineCache("users2-cache",
                Caffeine.newBuilder()
                        // 指定Key下的最大缓存数据量
                        .maximumSize(1000)
                        // 最后一次访问之后 120秒 过期
                        .expireAfterAccess(120, TimeUnit.SECONDS)
                        .build()));
        cacheManager.setCaches(caches);
        return cacheManager;
    }
}
