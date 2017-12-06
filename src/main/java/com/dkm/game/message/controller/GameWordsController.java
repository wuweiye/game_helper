package com.dkm.game.message.controller;

import com.dkm.base.BaseReq;
import com.dkm.basic.component.ext.web.BaseController;
import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.game.message.entity.GameWordsEntity;
import com.dkm.game.message.params.GameReplyAddParams;
import com.dkm.game.message.params.GameWordsAddParams;
import com.dkm.game.message.req.GameWordsQueryReq;
import com.dkm.game.message.req.GameWordsReq;
import com.dkm.game.message.service.GameWordsService;
import com.sun.org.apache.regexp.internal.RE;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping(value = "game/words/" , method = RequestMethod.POST)
@RestController
public class GameWordsController extends BaseController{


    @Autowired
    GameWordsService gameWordsService;

    @RequestMapping(value = "query")
    public ResponseEntity<PageResp<GameWordsQueryReq>> query(HttpServletRequest request,
                                                        @And({  @Spec(params = "gid",path = "gid",spec = Equal.class)})
                                                                Specification<GameWordsEntity> spec,
                                                        @RequestParam int page,
                                                        @RequestParam int rows){

        Pageable pageable = new PageRequest(page -1,rows,new Sort(new Sort.Order(Sort.Direction.DESC,"createTime")));

        PageResp<GameWordsQueryReq> resp = this.gameWordsService.gameWordsReqPageQuery(spec,pageable);


        return new ResponseEntity<PageResp<GameWordsQueryReq>>(resp, HttpStatus.OK);
    }


    @RequestMapping(value = "add/words")
    public ResponseEntity<BaseResp> addWords(@Valid GameWordsAddParams params){

        //String operator = super.getLoginUser();
        BaseResp resp = gameWordsService.addWords(params);




        return new ResponseEntity<BaseResp> (resp,HttpStatus.OK);
    }


    @RequestMapping(value = "add/reply")
    public ResponseEntity<BaseResp> addReply(@Valid GameReplyAddParams params){

        //String operator = super.getLoginUser();
        BaseResp resp = gameWordsService.addReplay(params);

        return new ResponseEntity<BaseResp> (resp,HttpStatus.OK);
    }


}
