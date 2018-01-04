package com.dkm.annotation.timer;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Aspect
@Component
public class TimerAspect {

    private static org.slf4j.Logger log = LoggerFactory.getLogger(TimerAspect.class);


    @Pointcut("@annotation(Timer)")
    public void testAspect(){
        log.info("enter  ok");
    }



    @Before("testAspect()")
    public void before(JoinPoint joinPoint){

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String beanName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        String uri = request.getRequestURI();
        String remoteAddr = getIpAddr(request);
        String method = request.getMethod();
        log.info("before  ===");
        String params = "";
        if ("POST".equals(method)) {
            Object[] paramsArray = joinPoint.getArgs();
            params = argsArrayToString(paramsArray);
        } else {
            Map<?, ?> paramsMap = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            params = paramsMap.toString();
        }

        log.info("uri=" + uri + "; beanName=" + beanName + "; remoteAddr=" + remoteAddr + "; methodName=" + methodName + "; params=" + params);

    }

    @Around("testAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("======执行环绕通知开始=========");
        Object[] args = joinPoint.getArgs();
        String method = joinPoint.getSignature().getName();
        Object target  = joinPoint.getTarget();

        Object result = joinPoint.proceed(args);

        args[1] = 100;
        System.out.println("输出,方法名：" + method + ";目标对象：" + target + ";返回值：" + result);
        System.out.println("======执行环绕通知结束=========");

        return result;
    }

    @After("testAspect()")
    public void after(){
        log.info("after ===== ");
    }


    @AfterThrowing(pointcut = "testAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e){

        log.info("doAfterThrowing  ----------");
       /* log.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget()
                        .getClass().getName()
                        + joinPoint.getSignature().getName(), e.getClass().getName(),
                e.getMessage());*/
    }

    @AfterReturning(returning = "result", pointcut = "testAspect()")
    public void afterReturning(Object result){

        log.info("AfterReturning :" + result.toString());

    }

    /**
     * 获取登录用户远程主机ip地址
     *
     * @param request
     * @return
     */
    private String getIpAddr(HttpServletRequest request) {
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
    private String argsArrayToString(Object[] paramsArray) {
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
