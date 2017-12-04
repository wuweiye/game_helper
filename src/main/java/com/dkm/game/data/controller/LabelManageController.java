package com.dkm.game.data.controller;

import com.dkm.basic.component.ext.web.BaseController;
import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.game.data.entity.GameLibrary;
import com.dkm.game.data.entity.LabelLibraryEntity;
import com.dkm.game.data.req.GameLibraryQueryReq;
import com.dkm.game.data.req.GameLibraryReq;
import com.dkm.game.data.req.LabelLibraryReq;
import com.dkm.game.data.service.LabelLibraryService;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
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

@RequestMapping(value = "/game/label/manage", produces = "application/json;charset=utf-8")
@RestController
public class LabelManageController extends BaseController{

    @Autowired
    LabelLibraryService labelLibraryService;

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<PageResp<LabelLibraryReq>> query(HttpServletRequest request,
                                                           @And({@Spec(params = "name", path = "name", spec = Like.class),
                                                                       @Spec(params = "status", path = "status", spec = In.class)})
                                                                       Specification<LabelLibraryEntity> spec,
                                                           @RequestParam int page,
                                                           @RequestParam int rows) {

        Pageable pageable = new PageRequest(page - 1, rows, new Sort(new Sort.Order(Sort.Direction.DESC, "createTime")));

        PageResp<LabelLibraryReq> rep = this.labelLibraryService.labelQuery(spec, pageable);

        return new ResponseEntity<PageResp<LabelLibraryReq>>(rep, HttpStatus.OK);
    }



    @RequestMapping("/add")
    @ResponseBody
    public ResponseEntity<BaseResp> add(@Valid GameLibraryReq req){
        String operator = super.getLoginUser();
        BaseResp rep = this.labelLibraryService.addGameLibrary(req, operator);

        return new ResponseEntity<BaseResp>(rep, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BaseResp> update(@Valid GameLibraryReq req) {
        String operator = super.getLoginUser();
        BaseResp rep = this.labelLibraryService.addGameLibrary(req, operator);

        return new ResponseEntity<BaseResp>(rep, HttpStatus.OK);
    }

}
