package com.dkm.annotation.base;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dkm.annotation.restRedis.RestRedis;
import com.dkm.listener.RedisService;
import com.dkm.utils.redis.KeyRedisUtil;
import com.dkm.utils.redis.ListRedisUtil;
import com.dkm.utils.redis.StrRedisUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;



public  abstract class BaseAspect {

    Logger log = LoggerFactory.getLogger(BaseAspect.class);

    @Autowired
    RedisService redisService;


    @Autowired
    public RedisTemplate<String, String> redis;

    //获取包名
    public String beanName = null;

    //方法名
    public String methodName = null;

    ///redis/save
    public String uri = null;

    //请求ip
    public String remoteAddr = null;

    public String method = null;

    public HttpServletRequest request;

    public String desc;

    public Object spec;

    public Integer page = 0;

    public Integer rows = 0;

    public  ProceedingJoinPoint pjp;
    protected abstract void pointcut();


    public void doBefore(JoinPoint joinPoint) throws Exception {




    }



    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        this.pjp = pjp;

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        this.request = attributes.getRequest();
        this.beanName = pjp.getSignature().getDeclaringTypeName();
        this.methodName = pjp.getSignature().getName();
        this.uri = request.getRequestURI();
        this.remoteAddr = getIpAddr(request);
        this.method = request.getMethod();
        if ("POST".equals(method)) {
            Object[] paramsArray = pjp.getArgs();


            if(paramsArray.length == 4){


                Object object = paramsArray[1];
                if(object == null){
                    log.info("00000000000000000000000000");
                }else {

                    spec = object;
                    log.info("222222222222222222222222222");
                }
                this.rows = (int) paramsArray[paramsArray.length - 1];

                this.page = (int) paramsArray[paramsArray.length - 2];
                log.info("page :"  + page);
            }


        }
        this.desc =  getDesc();
        return null;
    }


    public void doAfter(JoinPoint joinPoint){

        log.info("thred start");
        redisService.onRedisEvent(desc,pjp,redis,getKey());
        log.info("doAfter end");

    }

    public void doAfterThrowing(JoinPoint joinPoint, Throwable e){}

    public void doAfterReturning(Object result){}


    protected void setData(ProceedingJoinPoint pjp){

    }


    protected String getKey(){

        return beanName + methodName  ;
    }

    private String getDesc(){

        try {
            Class clazz = Class.forName(beanName);
            Method[] methods = clazz.getDeclaredMethods();
            for(Method method : methods){
                if(method.getName().equals(methodName)){
                    for(Annotation annotation : method.getDeclaredAnnotations()){
                        if(method.isAnnotationPresent(RestRedis.class)){

                            RestRedis restRedis = method.getAnnotation(RestRedis.class);

                            System.out.print("rest redis");
                            return restRedis.desc();

                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "string";
    }

    @Async
    protected void save(){
        Object obj = null;
        try {
            log.info("------------------");
            obj = pjp.proceed();
            if(desc.equals("String")){
                StrRedisUtil.set(redis,getKey(),obj);
                KeyRedisUtil.expire(redis,getKey(),600);
            }else if(desc.equals("list")){

                ListRedisUtil.setRightPush(redis, getKey(), JSON.toJSONString(obj));
                KeyRedisUtil.expire(redis,getKey(),600);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }



    /**
     * 获取登录用户远程主机ip地址
     *
     * @param request
     * @return
     */
    protected String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 请求参数拼装
     *
     * @param paramsArray
     * @return
     */
    protected String argsArrayToString(Object[] paramsArray) {

        String params = "";
        if (paramsArray != null && paramsArray.length > 0) {
            for (int i = 0; i < paramsArray.length; i++) {
                Object jsonObj = JSON.toJSON(paramsArray[i]);
                if (jsonObj == null) {
                    params +=  " ";
                    continue;
                }
                params += jsonObj.toString() + " ";
            }
        }
        return params.trim();
    }

}
