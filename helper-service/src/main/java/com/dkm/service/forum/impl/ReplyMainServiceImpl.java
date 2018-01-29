package com.dkm.service.forum.impl;

import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.dao.forum.ReplyMainDao;
import com.dkm.event.ForumsEventService;
import com.dkm.model.forum.ReplyMain;
import com.dkm.myenum.GameEnum;
import com.dkm.params.forum.ReplyParams;
import com.dkm.service.forum.ReplyMainService;
import com.dkm.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ReplyMainServiceImpl implements ReplyMainService {


    @Autowired
    ReplyMainDao replyMainDao;

    @Autowired
    ForumsEventService forumsEventService;

    @Override
    public void save(ReplyParams params) {

        ReplyMain replyMain;

        if(StringUtils.isEmpty(params.getId())){
            replyMain = new ReplyMain();

        }else {
            replyMain = replyMainDao.getOne(params.getId());
        }

        replyMain.setUid(params.getUid());
        replyMain.setContent(params.getContent());
        replyMain.setPid(params.getPid());

        replyMainDao.saveAndFlush(replyMain);

        forumsEventService.onAddManageEvent(replyMain.getId(), "replyMain");

    }



    @Override
    public BaseResp delete(Long id) {

        BaseResp rep = new BaseResp();
        ReplyMain replyMain = replyMainDao.getOne(id);
        if(replyMain == null){
            return new BaseResp(-1, "无效的 id");
        }

        replyMain.setStatus(GameEnum.Status.DELETE.getValue());
        replyMainDao.saveAndFlush(replyMain);

        forumsEventService.onDeleteEvent(id, "reply");

        return rep;
    }

    @Override
    public void deleteAll(Long id) {
        ReplyMain replyMain = replyMainDao.getOne(id);
        if(replyMain != null){
            replyMain.setStatus(GameEnum.Status.DELETE.getValue());
            replyMainDao.saveAndFlush(replyMain);
        }


    }


    @Override
    public PageResp<ReplyMain> replyMainQuery(Specification<ReplyMain> spec, Pageable pageable) {

        Page<ReplyMain> replyMains = replyMainDao.findAll(spec, pageable);

        PageResp<ReplyMain> rep = new PageResp<ReplyMain>();


        //TODO : 后期调整返回值
        for(ReplyMain replyMain : replyMains){
            rep.getRows().add(replyMain);
        }


        return rep;
    }


}
