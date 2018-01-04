package com.dkm.boot.controller.article;

import com.dkm.basic.component.ext.web.BaseController;
import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.model.game.GameArticleEntity;
import com.dkm.service.app.GameDetailService;
import com.dkm.params.article.GameArticleParams;
import com.dkm.resp.article.GameArticleReq;
import com.dkm.service.article.GameArticleService;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping(value = "/game/article/manage/")
@RestController
public class GameArticleController extends BaseController {

    @Autowired
    GameArticleService gameArticleService;

    @Autowired
    GameDetailService gameDetailService;

    @RequestMapping(value = "query", method = RequestMethod.POST)
    public ResponseEntity<PageResp<GameArticleReq>> query(HttpServletRequest request,
                                                          @And({@Spec(params = "gid", path = "gid", spec = Equal.class),
                                                                     @Spec(params = "title", path = "title", spec = Like.class),
                                                                     @Spec(params = "status", path = "status", spec = Equal.class)})
                                                                     Specification<GameArticleEntity> spec,
                                                          @RequestParam int page,
                                                          @RequestParam int rows){

        Pageable pageable = new PageRequest(page - 1, rows, new Sort(new Sort.Order(Sort.Direction.DESC, "createTime")));
        PageResp<GameArticleReq> rep = this.gameArticleService.gameArticleQuery(spec, pageable);

        return new ResponseEntity<PageResp<GameArticleReq>>(rep, HttpStatus.OK);
    }



    @RequestMapping(value = "add")
    public ResponseEntity<BaseResp> add(@Valid GameArticleParams params){


        String operator = super.getLoginUser();
        BaseResp resp = gameArticleService.add(params,operator);

        return new ResponseEntity<BaseResp>(resp,HttpStatus.OK);
    }


    @RequestMapping(value = "update")
    public ResponseEntity<BaseResp> update(@Valid GameArticleParams params){

        String operator = super.getLoginUser();
        BaseResp resp = gameArticleService.add(params,operator);

        return new ResponseEntity<BaseResp>(resp,HttpStatus.OK);
    }


    @RequestMapping(value = "delete")
    public ResponseEntity<BaseResp> delete(@RequestParam Long id){

        String operator = super.getLoginUser();
        BaseResp resp = gameArticleService.detele(id,operator);

        return new ResponseEntity<BaseResp>(resp,HttpStatus.OK);
    }



}
