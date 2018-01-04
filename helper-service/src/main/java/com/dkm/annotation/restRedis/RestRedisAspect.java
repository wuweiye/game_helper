package com.dkm.annotation.restRedis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.dkm.annotation.base.BaseAspect;
import com.dkm.resp.app.GameDetailReq;
import com.dkm.utils.StringUtils;
import com.dkm.utils.redis.HashRedisUtil;
import com.dkm.utils.redis.KeyRedisUtil;
import com.dkm.utils.redis.StrRedisUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

@Aspect
@Component
public class RestRedisAspect extends BaseAspect{

    Logger log = LoggerFactory.getLogger(RestRedisAspect.class);

    @Autowired
    RedisTemplate<String, String> redis;



    @Pointcut("@annotation(RestRedis)")
    @Override
    protected void pointcut() {

    }


    @Override
    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        super.doBefore(joinPoint);



    }

    @Override
    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        String result = StrRedisUtil.get(redis, getKey());


        if(!StringUtils.isEmpty(result)){

            log.info("redis存在  直接返回");
            JSONObject jsonObject = JSON.parseObject(result);
            JSONObject gameDetailReq = jsonObject.getJSONObject("body");
            return new ResponseEntity<JSONObject>(gameDetailReq, HttpStatus.OK);
        }else {
            log.info("redis");
            Object obj =  super.doAround(pjp);
            log.info("redis不存在  ----- 开始保存/更新");
            StrRedisUtil.set(redis,getKey(),obj);
            KeyRedisUtil.expire(redis,getKey(),60);
            return obj;
        }

    }

    @Override
    @AfterReturning(pointcut = "pointcut()" ,returning = "result")
    public void doAfterReturning(Object result) {


    }

    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
           //序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
        }
        return null;
    }

    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            //反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
        }
        return null;
    }
}
