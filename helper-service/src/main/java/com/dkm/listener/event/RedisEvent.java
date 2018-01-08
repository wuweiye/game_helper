package com.dkm.listener.event;

import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.data.redis.core.RedisTemplate;

@Data
public class RedisEvent {

    String desc ;

    ProceedingJoinPoint pjp;

    RedisTemplate<String, String> redis;

    String key;
}
