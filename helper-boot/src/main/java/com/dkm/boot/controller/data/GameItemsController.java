package com.dkm.boot.controller.data;

import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.model.data.GameItemsEntity;
import com.dkm.params.data.GameItemsParams;
import com.dkm.resp.data.GameItemsReq;
import com.dkm.service.data.GameItemsService;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RequestMapping(value = "/game/items/", produces = "application/json;charset=utf-8")
@RestController
public class GameItemsController {

    @Autowired
    GameItemsService gameItemsService;



    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<PageResp<GameItemsReq>> query(HttpServletRequest request,
                                                        @And({@Spec(params = "gid", path = "gid", spec = Equal.class)})
                                                                       Specification<GameItemsEntity> spec,
                                                        @RequestParam int page,
                                                        @RequestParam int rows) {

        Pageable pageable = new PageRequest(page - 1, rows, new Sort(new Sort.Order(Sort.Direction.DESC, "createTime")));

        PageResp<GameItemsReq> rep = this.gameItemsService.query(spec, pageable);

        return new ResponseEntity<PageResp<GameItemsReq>>(rep, HttpStatus.OK);
    }


    @RequestMapping(value = "add")
    public ResponseEntity<BaseResp> add(@Valid GameItemsParams params){

        BaseResp resp = gameItemsService.add(params);

        return new ResponseEntity<BaseResp>(resp,HttpStatus.OK);

    }

    @RequestMapping(value = "update")
    public ResponseEntity<BaseResp> update(@Valid GameItemsParams params){

        BaseResp resp = gameItemsService.add(params);

        return new ResponseEntity<BaseResp>(resp,HttpStatus.OK);

    }

    @RequestMapping(value = "delete")
    public ResponseEntity<BaseResp> delete(@RequestParam Long id){

        BaseResp resp = gameItemsService.delete(id);

        return new ResponseEntity<BaseResp>(resp,HttpStatus.OK);

    }


    @RequestMapping(value = "test")
    public String test(){
        gameItemsService.save();


        return "ok";
    }
}
