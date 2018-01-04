package com.dkm.annotation.base;


import com.alibaba.fastjson.JSON;
import com.dkm.exception.MyException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


public  abstract class BaseAspect {

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


    public String params;

    protected abstract void pointcut();


    public void doBefore(JoinPoint joinPoint) throws Exception {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
         request = attributes.getRequest();
         beanName = joinPoint.getSignature().getDeclaringTypeName();
         methodName = joinPoint.getSignature().getName();
         uri = request.getRequestURI();
         remoteAddr = getIpAddr(request);
         method = request.getMethod();
        if ("POST".equals(method)) {
            Object[] paramsArray = joinPoint.getArgs();
            params = argsArrayToString(paramsArray);
        }
    }

    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        return pjp.proceed();
    }

    public void doAfter(JoinPoint joinPoint){ }

    public void doAfterThrowing(JoinPoint joinPoint, Throwable e){}

    public void doAfterReturning(Object result){}



    protected String getKey(){
        return beanName + methodName;
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
