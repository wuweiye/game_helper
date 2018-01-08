package com.dkm.annotation.restRedis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dkm.annotation.base.BaseAspect;
import com.dkm.model.data.GameLibrary;
import com.dkm.utils.redis.ListRedisUtil;
import com.dkm.utils.redis.StrRedisUtil;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.apache.tools.ant.taskdefs.optional.extension.Specification;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;


@Aspect
@Component
public class RestRedisAspect extends BaseAspect{

    Logger log = LoggerFactory.getLogger(RestRedisAspect.class);

    private Object obj = null;




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
        log.info("doAround  start");
        super.doAround(pjp);
        String result = null;
        if(desc.equals("String")){
            result = StrRedisUtil.get(redis, getKey());
        }else if(desc.equals("list")){
             result = ListRedisUtil.getIndex(redis,getKey(),page-1);
        }


        if(result != null && !result.equals("null")){
            JSONObject jsonObject = JSON.parseObject(result);
            JSONObject gameDetailReq = jsonObject.getJSONObject("body");
            return new ResponseEntity<JSONObject>(gameDetailReq, HttpStatus.OK);
        }

        obj =  pjp.proceed();
        log.info("doAround  end");
        return obj;
    }

    @Override
    @AfterReturning(pointcut = "pointcut()" ,returning = "result")
    public void doAfterReturning(Object result) {

        try {
            Class clazz = Class.forName(beanName);
            Object o = clazz.newInstance();
            Method[] methods = clazz.getDeclaredMethods();

            for (Method method : methods){
                if(method.getName() == methodName){


                    Object result2 = method.invoke(o,1,10);
                }
            }


            int i =1;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    @After("pointcut()")
    public void doAfter(JoinPoint joinPoint) {
        super.doAfter(joinPoint);

    }


}
