package com.dkm.annotation.restRedis;

import com.dkm.annotation.base.BaseAspect;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RestRedisAspect extends BaseAspect{


    @Pointcut("@annotation(RestRedis)")
    @Override
    protected void pointcut() {

    }
}
