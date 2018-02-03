package com.dkm.boot.controller.app.center;

import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.model.forum.Forums;
import com.dkm.resp.app.ForumReq;
import com.dkm.service.forum.ForumsService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/center/forums")
public class ForumsController {

    @Autowired
    ForumsService forumsService;

    @RequestMapping(value = "get/forums")
    @ResponseBody
    public ResponseEntity<PageResp<ForumReq>> query(@And({@Spec(params = "name", path = "name", spec = Like.class),
                                                                       @Spec(params = "status", path = "status", spec = Equal.class)})
                                                            Specification<Forums> spec,
                                                    @RequestParam int page,
                                                    @RequestParam int rows) {

        Pageable pageable = new PageRequest(page - 1, rows, new Sort(new Sort.Order(Sort.Direction.DESC, "createTime")));

        PageResp<ForumReq> rep = forumsService.forumsQuery(spec, pageable);

        return new ResponseEntity<PageResp<ForumReq>>(rep, HttpStatus.OK);
    }

    //创建 随游戏一起
    public BaseResp createForums(){


        return null;
    }
}
