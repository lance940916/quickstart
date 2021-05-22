package com.snailwu.ehcache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

/**
 * @author WuQinglong
 * @date 2021/2/23 11:24
 */
public class EhCacheMain {

    public static void main(String[] args) {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);

        Cache<String, String> localCache = cacheManager.createCache("localCache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(
                        String.class, String.class, ResourcePoolsBuilder.heap(100)
                ).build()
        );

        localCache.put("name", "Mike");
        System.out.println(localCache.get("name"));
    }

}
