package com.dkm.boot.controller.article;

import com.dkm.admin.operate.system.Sys;
import com.dkm.annotation.timer.Timer;
import com.dkm.base.Constants;
import com.dkm.service.Test2Service;
import com.dkm.utils.redis.KeyRedisUtil;
import com.dkm.utils.redis.StrRedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "redis/")
@RestController
public class Test2Controller {


    @Autowired
    RedisTemplate<String, String> redis;

    private static final String key = "GAME_DETAIL";

    @Autowired
    Test2Service testService;

    @RequestMapping("save")
    public String test(@RequestParam("value") String flag, @RequestParam("time")int time){


        Constants.sys("flag"+ flag +"-- time:" + time + this.getClass());

        StrRedisUtil.set(redis,key,flag);
        KeyRedisUtil.expire(redis, key, time);

        return  "save success";
    }


    @RequestMapping("get")
    public String get(){

        return  StrRedisUtil.get(redis,key) == null ? "null" : StrRedisUtil.get(redis,key);
    }


    @Timer
    @RequestMapping("del")
    public String del(){
        StrRedisUtil.del(redis,key);

        return "del success";
    }

    @Timer
    @RequestMapping("err")
    public String error(){

        int i = 100/0;

        return "error110";
    }


    @Timer
    @RequestMapping("error")
    public void empty(){
        System.out.println("this is a empty method");
    }




}
