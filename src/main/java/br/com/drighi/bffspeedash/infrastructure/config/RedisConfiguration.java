package br.com.drighi.bffspeedash.infrastructure.config;

import br.com.drighi.bffspeedash.domain.model.cliente.ClienteDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfiguration {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();

        // Serialização de Chaves como Strings
        template.setKeySerializer(new StringRedisSerializer());

        // Serialização de Valores com Jackson
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(new ObjectMapper()
                .registerModule(new JavaTimeModule()) // Suporte para datas (Java 8)
                .registerModule(new SimpleModule())
        );

        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);

        template.setConnectionFactory(redisConnectionFactory);

        return template;
    }

}