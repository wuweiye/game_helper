package com.dkm.boot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/*@ControllerAdvice*/
public class GlobalDefaultExceptionHandler {



    /*@ResponseBody
    @ExceptionHandler(value = Exception.class)*/
    public String defaultExceptionHandle(HttpServletRequest req, Exception e){


        System.out.println("---捕捉到异常-----" + e.getMessage()  + "----");


        return "捕捉到异常";

    }
}
