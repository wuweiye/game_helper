package com.dkm.boot.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public String defaultExceptionHandle(HttpServletRequest req, Exception e){

        e.printStackTrace();
        System.out.println("---捕捉到异常-----");



        return "捕捉到异常";

    }
}
