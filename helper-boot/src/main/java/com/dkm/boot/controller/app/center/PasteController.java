package com.dkm.boot.controller.app.center;

import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.model.forum.Forums;
import com.dkm.model.forum.Paste;
import com.dkm.params.forum.PasteParams;
import com.dkm.resp.app.ForumReq;
import com.dkm.resp.app.PasteDetailReq;
import com.dkm.resp.app.PasteReq;
import com.dkm.service.forum.PasteService;
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
@RequestMapping("/app/center/paste")
public class PasteController {


    @Autowired
    PasteService pasteService;

    @RequestMapping(value = "getList")
    public ResponseEntity<PageResp<PasteReq>> query(@And({@Spec(params = "name", path = "name", spec = Like.class),
            @Spec(params = "status", path = "status", spec = Equal.class)})
                                                            Specification<Paste> spec,
                                                    @RequestParam int page,
                                                    @RequestParam int rows) {

        Pageable pageable = new PageRequest(page - 1, rows, new Sort(new Sort.Order(Sort.Direction.DESC, "createTime")));

        PageResp<PasteReq> rep = pasteService.replyMainQuery(spec, pageable);

        return new ResponseEntity<PageResp<PasteReq>>(rep, HttpStatus.OK);
    }


    @RequestMapping(value = "add/paste")
    public BaseResp add(PasteParams params){

        return pasteService.save(params);
    }

    public ResponseEntity<PageResp<PasteReq>> getDetail(@RequestParam Long id) {


        PageResp<PasteDetailReq> rep = pasteService.getPasteDetail(id);

        return null;

        //return new ResponseEntity<PageResp<PasteReq>>(rep, HttpStatus.OK);
    }

}
