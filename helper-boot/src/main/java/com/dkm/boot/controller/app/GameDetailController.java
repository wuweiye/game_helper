package com.dkm.boot.controller.app;


import com.dkm.resp.app.GameDetailReq;
import com.dkm.service.app.GameDetailService;
import com.dkm.params.data.GameDetailParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/app/game/")
public class GameDetailController {

    @Autowired
    GameDetailService gameDetailService;

    @RequestMapping(value = "get/detail" ,method = RequestMethod.POST)
    public ResponseEntity<GameDetailReq> getGameDetail(@Valid GameDetailParams params){

        GameDetailReq req = gameDetailService.getDetail(params);

        return new ResponseEntity<GameDetailReq>(req, HttpStatus.OK);
    }
}
