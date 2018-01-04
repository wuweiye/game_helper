package com.dkm.boot.controller.app;


import com.dkm.basic.component.ext.web.BaseController;
import com.dkm.resp.app.GameDetailReq;
import com.dkm.service.app.GameDetailService;
import com.dkm.params.data.GameDetailParams;
import com.dkm.utils.redis.StrRedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/app/game/")
public class GameDetailController extends BaseController {


    @Autowired
    GameDetailService gameDetailService;

    @Autowired
    RedisTemplate<String,String> redis;

    @RequestMapping(value = "get/detail" ,method = RequestMethod.POST)
    public ResponseEntity<GameDetailReq> getGameDetail(@Valid GameDetailParams params){


        GameDetailReq req = gameDetailService.getDetail(params);


        return new ResponseEntity<GameDetailReq>(req, HttpStatus.OK);
    }
}
