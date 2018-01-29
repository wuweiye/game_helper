package com.dkm.boot.controller.test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloOAuthController {

  /*  @Autowired
    RedisTemplate<String, byte[]> redis;

    @RequestMapping(method = RequestMethod.GET)
    public String sayHello(){

        byte[] bytes = {'a', 'b'};

        System.out.println(bytes[1]);
        redis.opsForList().set("123",1000, bytes);


        byte[]  b =  redis.opsForValue().get("123");

        System.out.println(b[1]);


        return "hello";

    }*/



}
