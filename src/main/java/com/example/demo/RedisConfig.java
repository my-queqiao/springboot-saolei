package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
	/**
	 *    整合redis，要求工厂、模板，实例必须分别交给spring容器，否则会报异常。
	 */
	@Bean
	public LettuceConnectionFactory a() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("39.106.188.246", 6379);
		redisStandaloneConfiguration.setPassword("131122ab");
		LettuceConnectionFactory lettuceFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);
		return lettuceFactory;
	}
	  @Bean
	  public RedisTemplate<String,Object> redisTemplate(LettuceConnectionFactory f) {
		  
		  RedisTemplate<String, Object> template = new RedisTemplate<>();
		  template.setConnectionFactory(f);
		  template.setKeySerializer(new StringRedisSerializer());
		  template.setValueSerializer(new StringRedisSerializer());
		  return template;
	  }
}
	  
	  
