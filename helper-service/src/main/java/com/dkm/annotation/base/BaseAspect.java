package com.dkm.annotation.base;


import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import javax.servlet.http.HttpServletRequest;


public  abstract class BaseAspect {

    public String beanName = null;

    public String methodName = null;

    public String uri = null;

    public String remoteAddr = null;

    public String method = null;

    protected abstract void pointcut();

    /*protected BaseAspect(){}*/

    public void doBefore(JoinPoint joinPoint){}

    public Object doAround(ProceedingJoinPoint joinPoint){
        return  null;
    }

    public void doAfter(JoinPoint joinPoint){ }

    public void doAfterThrowing(JoinPoint joinPoint, Throwable e){}

    public void afterReturning(Object result){}



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
