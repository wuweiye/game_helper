package com.dkm.boot.controller.test;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloOAuthController {


    @RequestMapping(method = RequestMethod.GET)
    public String sayHello(){
        return "hello";
    }



}
