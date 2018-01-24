package com.dkm.boot.controller.app;

import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.model.data.GameItemsEntity;
import com.dkm.model.data.GameLibrary;
import com.dkm.resp.data.GameItemsReq;
import com.dkm.service.data.GameItemsService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/item")
public class GameItemController {

    @Autowired
    GameItemsService gameItemsService;


    @RequestMapping("get/item")
    public ResponseEntity<PageResp<GameItemsReq>> getItem(@And({@Spec(params = "name", path = "name", spec = Like.class),
                                                  @Spec(params = "id", path = "id", spec = Equal.class)})
                                                  Specification<GameItemsEntity> spec,
                                                          @RequestParam int page,
                                                          @RequestParam int rows){


        Pageable pageable = new PageRequest(page - 1, rows, new Sort(new Sort.Order(Sort.Direction.DESC, "createTime")));

        PageResp<GameItemsReq> rep = this.gameItemsService.query(spec, pageable);

        return new ResponseEntity<PageResp<GameItemsReq>>(rep, HttpStatus.OK);


    }


}
