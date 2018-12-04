package com.xsomnus.forumsignin.config;

import com.xsomnus.forumsignin.pojo.entity.Member;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author somnus_xiawenye
 * @since 2018/12/4 0004 9:43
 */
@Configuration
public class MemberConfiguration {

    @Bean
    ReactiveRedisOperations<String, Member> redisOperations(ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<Member> serializer = new Jackson2JsonRedisSerializer<>(Member.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, Member> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());
        RedisSerializationContext<String, Member> context = builder.value(serializer).build();
        return new ReactiveRedisTemplate<>(factory, context);
    }

}
