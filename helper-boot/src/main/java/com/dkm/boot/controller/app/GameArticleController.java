package com.dkm.boot.controller.app;

import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.model.game.GameArticleEntity;
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

@RestController
@RequestMapping("app/article")
public class GameArticleController {

    @Autowired
    GameArticleService gameArticleService;

    @RequestMapping(value = "get", method = RequestMethod.POST)
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
}
