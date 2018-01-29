package com.dkm.boot.controller.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dkm.admin.operate.system.Sys;
import com.dkm.annotation.timer.Timer;
import com.dkm.base.Constants;
import com.dkm.model.user.UserEntity;
import com.dkm.service.Test2Service;
import com.dkm.utils.redis.KeyRedisUtil;
import com.dkm.utils.redis.StrRedisUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.swing.text.html.parser.Entity;
import java.util.Date;
import java.util.List;

@RequestMapping(value = "redis/")
@RestController
public class Test2Controller {


    @Autowired
    RedisTemplate<String,String> redis;

    private static final String key = "GAME_DETAIL";

    @Autowired
    Test2Service testService;

    @RequestMapping("save")
    public String test(@RequestParam("value") String flag, @RequestParam("time")int time){


        UserEntity entity = new UserEntity();

        entity.setRegIp("123");
        entity.setUserName("name");
        entity.setLastLoginTime(new Date());

        String json = JSON.toJSONString(entity);

        StrRedisUtil.set(redis,key,entity);
        KeyRedisUtil.expire(redis, key, time);

        return  "save success";
    }

    @RequestMapping("get")
    public String get(){

        String json = StrRedisUtil.get(redis,key) == null ? "null" : StrRedisUtil.get(redis,key);
        JSONObject jsStr = JSONObject.parseObject(json);
        UserEntity entity = JSONObject.toJavaObject(jsStr,UserEntity.class);

        return  json;
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
