//package com.zapflow.config;
//
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import java.time.Duration;
//
//@Configuration
//@EnableCaching            // só se for usar @Cacheable etc.
//public class RedisConfig {
//
//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//        return new LettuceConnectionFactory("localhost", 6379);
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(
//            LettuceConnectionFactory factory) {
//        RedisTemplate<String,Object> tpl = new RedisTemplate<>();
//        tpl.setConnectionFactory(factory);
//        tpl.setKeySerializer(new StringRedisSerializer());
//        tpl.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        return tpl;
//    }
//
//    @Bean
//    public CacheManager cacheManager(LettuceConnectionFactory factory) {
//        RedisCacheConfiguration cfg = RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofMinutes(10));
//        return RedisCacheManager.builder(factory)
//                .cacheDefaults(cfg)
//                .build();
//    }
//}
