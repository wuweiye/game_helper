package com.dkm.game.data.controller;

import com.dkm.basic.component.ext.web.BaseController;
import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.game.data.entity.GameAssessEntity;
import com.dkm.game.data.entity.GameLibrary;
import com.dkm.game.data.req.GameAssessUpdateReq;
import com.dkm.game.data.req.GameLibraryQueryReq;
import com.dkm.game.data.req.GameLibraryReq;
import com.dkm.game.data.service.DataManageService;
import com.dkm.basic.component.ext.web.PageResp;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping(value = "/game/data/manage", produces = "application/json;charset=utf-8")
@RestController
public class DataManageController extends BaseController {

    @Autowired
    DataManageService dataManageService;





    @RequestMapping("/add")
    @ResponseBody
    public ResponseEntity<BaseResp> add(@Valid GameLibraryReq req){
        String operator = super.getLoginUser();
        BaseResp rep = this.dataManageService.addGameLibrary(req, operator);

        return new ResponseEntity<BaseResp>(rep, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BaseResp> update(@Valid GameLibraryReq req) {
        String operator = super.getLoginUser();
        BaseResp rep = this.dataManageService.addGameLibrary(req, operator);

        return new ResponseEntity<BaseResp>(rep, HttpStatus.OK);
    }



    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<PageResp<GameLibraryQueryReq>> query(HttpServletRequest request,
                                                               @And({@Spec(params = "name", path = "name", spec = Like.class),
                                                                  @Spec(params = "status", path = "status", spec = Equal.class)})
                                                                  Specification<GameLibrary> spec,
                                                               @RequestParam int page,
                                                               @RequestParam int rows) {

        Pageable pageable = new PageRequest(page - 1, rows, new Sort(new Order(Sort.Direction.DESC, "createTime")));

        PageResp<GameLibraryQueryReq> rep = this.dataManageService.gameLibraryQuery(spec, pageable);

        return new ResponseEntity<PageResp<GameLibraryQueryReq>>(rep, HttpStatus.OK);
    }



    public  ResponseEntity<BaseResp> updateStar(GameAssessUpdateReq req){
        String operator = super.getLoginUser();
        BaseResp rep = this.dataManageService.updateStar(req, operator);

        return new ResponseEntity<BaseResp>(rep, HttpStatus.OK);
    }


    @RequestMapping(value = "get/game")
    public ResponseEntity<PageResp<GameLibraryReq>> getGame(){

        PageResp<GameLibraryReq> resp = this.dataManageService.getGame();


        return new ResponseEntity<PageResp<GameLibraryReq>>(resp,HttpStatus.OK);
    }


    @RequestMapping(value = "test")
    public String test(){

        dataManageService.save(new GameLibrary());

        return "ok" ;
    }

}
