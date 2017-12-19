package com.dkm.game.app.controller;


import com.dkm.game.app.req.GameDetailReq;
import com.dkm.game.app.service.GameDetailService;
import com.dkm.game.article.params.GameArticleParams;
import com.dkm.game.data.params.GameDetailParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/game/detail/")
public class GameDetailController {

    @Autowired
    GameDetailService gameDetailService;

    @RequestMapping(value = "get/detail")
    public ResponseEntity<GameDetailReq> getGameDetail(@RequestParam GameDetailParams params){

        GameDetailReq req = gameDetailService.getDetail(params);

        return new ResponseEntity<GameDetailReq>(req, HttpStatus.OK);
    }
}
