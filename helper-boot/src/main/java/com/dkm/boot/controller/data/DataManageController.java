package com.dkm.boot.controller.data;

import com.dkm.basic.component.ext.web.BaseController;
import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.resp.app.GameDetailReq;
import com.dkm.service.app.GameDetailService;
import com.dkm.model.data.GameLibrary;
import com.dkm.params.data.GameDetailParams;
import com.dkm.params.data.GameLibraryParams;
import com.dkm.resp.data.GameAssessUpdateReq;
import com.dkm.resp.data.GameLibraryQueryReq;
import com.dkm.resp.data.GameLibraryReq;
import com.dkm.service.data.DataManageService;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping(value = "/game/data/manage", produces = "application/json;charset=utf-8")
@RestController
public class DataManageController extends BaseController {

    @Autowired
    DataManageService dataManageService;

    @Autowired
    GameDetailService gameDetailService;





    @RequestMapping("/add")
    @ResponseBody
    public ResponseEntity<BaseResp> add(@Valid GameLibraryParams params){
        String operator = super.getLoginUser();
        BaseResp rep = this.dataManageService.addGameLibrary(params, operator);

        return new ResponseEntity<BaseResp>(rep, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BaseResp> update(@Valid GameLibraryParams params) {
        String operator = super.getLoginUser();
        BaseResp rep = this.dataManageService.addGameLibrary(params, operator);

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


    @RequestMapping(value = "detail" ,method = RequestMethod.POST)
    public ResponseEntity<GameDetailReq> getGameDetail(@Valid GameDetailParams params){

        GameDetailReq req = gameDetailService.getDetail(params);

        return new ResponseEntity<GameDetailReq>(req, HttpStatus.OK);
    }


    @RequestMapping(value = "test")
    public String test(){

        dataManageService.save(new GameLibrary());

        return "ok" ;
    }


}
