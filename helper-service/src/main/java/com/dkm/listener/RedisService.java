package com.dkm.listener;

import com.alibaba.fastjson.JSON;
import com.dkm.annotation.base.BaseAspect;
import com.dkm.listener.event.RedisEvent;
import com.dkm.utils.redis.KeyRedisUtil;
import com.dkm.utils.redis.ListRedisUtil;
import com.dkm.utils.redis.StrRedisUtil;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Aspect
@AllArgsConstructor
public class RedisService {

    ApplicationEventPublisher eventPublisher;



    public void onRedisEvent(String desc, ProceedingJoinPoint pjp, RedisTemplate<String, String> redis, String key){

        System.out.println("redis event start");

        RedisEvent event = new RedisEvent();
        event.setDesc(desc);
        event.setKey(key);
        event.setPjp(pjp);
        event.setRedis(redis);
        eventPublisher.publishEvent(event);

    }



    @Async
    @EventListener
    public void OnListener(RedisEvent event){

        Object obj = null;
        try {

            obj = event.getPjp().proceed();
            if(event.getDesc().equals("String")){
                StrRedisUtil.set(event.getRedis(),event.getKey(),obj);
                KeyRedisUtil.expire(event.getRedis(),event.getKey(),600);
            }else if(event.getDesc().equals("list")){

                ListRedisUtil.setRightPush(event.getRedis(),event.getKey(), JSON.toJSONString(obj));
                KeyRedisUtil.expire(event.getRedis(),event.getKey(),600);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        Logger log = LoggerFactory.getLogger(RedisService.class);
        log.info("thred start");
    }
}
