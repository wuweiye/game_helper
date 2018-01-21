package com.dkm.boot.filter;

import com.dkm.utils.IPUtil;
import com.dkm.utils.redis.StrRedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import sun.rmi.runtime.Log;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


@WebFilter(filterName = "myFilter", urlPatterns = "/app/center/*")
public class MyFilter implements Filter {

    @Autowired
    RedisTemplate<String, String> redis;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        System.out.println("过滤器初始化");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        System.out.println("执行过滤操作");
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        String ip = IPUtil.getIp(httpRequest);


        Map<String,String> parmMap=new HashMap<String,String>();

        Map<String,String[]> map= httpRequest.getParameterMap();
        Set<String> key=map.keySet();

        Iterator<String> iterator = key.iterator();

        boolean exists = false;
        while(iterator.hasNext()){
            String k=iterator.next();

            if(k.equals("key")){
                String redisKey = map.get(k)[0];

                exists = StrRedisUtil.exists(redis, redisKey);
                break;
            }

        }

        if(!exists){

            System.out.println("redis 不存在， -------------------拦截处理---------");

        }


        filterChain.doFilter(servletRequest, servletResponse);

    }

    /*if (isInclude(url)){
            chain.doFilter(httpRequest, httpResponse);
            return;
        } else {
            HttpSession session = httpRequest.getSession();
            if (session.getAttribute("") != null){
                // session存在
                chain.doFilter(httpRequest, httpResponse);
                return;
            } else {
                // session不存在 准备跳转失败
                *//* RequestDispatcher dispatcher = request.getRequestDispatcher(path);
                    dispatcher.forward(request, response);*//*
                chain.doFilter(httpRequest, httpResponse);
                return;
            }
        }*/

    @Override
    public void destroy() {

        System.out.println("过滤器销毁");
    }
}
