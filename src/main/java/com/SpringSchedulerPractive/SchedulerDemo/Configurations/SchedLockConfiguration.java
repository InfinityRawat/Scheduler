package com.SpringSchedulerPractive.SchedulerDemo.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.redis.spring.RedisLockProvider;

@Configuration
public class SchedLockConfiguration {

	@Bean
	LockProvider redisLockProvider(RedisConnectionFactory redisFactor) {
		return new RedisLockProvider(redisFactor);
	}
}
