package com.dkm.game.app.controller;

import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.game.app.req.GameDetailReq;
import com.dkm.game.data.entity.GameLibrary;
import com.dkm.game.data.req.GameLibraryQueryReq;
import com.dkm.game.data.service.DataManageService;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
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

@RestController
@RequestMapping(value = "/app/manage/")
public class GameManageController {

    @Autowired
    DataManageService dataManageService;

    @RequestMapping(value = "get/games")
    @ResponseBody
    public ResponseEntity<PageResp<GameLibraryQueryReq>> query(HttpServletRequest request,
                                                               @And({@Spec(params = "name", path = "name", spec = Like.class),
                                                                       @Spec(params = "status", path = "status", spec = Equal.class)})
                                                                       Specification<GameLibrary> spec,
                                                               @RequestParam int page,
                                                               @RequestParam int rows) {

        Pageable pageable = new PageRequest(page - 1, rows, new Sort(new Sort.Order(Sort.Direction.DESC, "createTime")));

        PageResp<GameLibraryQueryReq> rep = this.dataManageService.gameLibraryQuery(spec, pageable);

        return new ResponseEntity<PageResp<GameLibraryQueryReq>>(rep, HttpStatus.OK);
    }
}
