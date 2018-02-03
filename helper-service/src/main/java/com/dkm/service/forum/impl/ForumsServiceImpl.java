package com.dkm.service.forum.impl;

import com.dkm.base.Constants;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.dao.forum.ForumsDao;
import com.dkm.event.ForumsEventService;
import com.dkm.model.data.GameLibrary;
import com.dkm.model.forum.Forums;
import com.dkm.myenum.GameEnum;
import com.dkm.resp.app.ForumReq;
import com.dkm.service.data.DataManageService;
import com.dkm.service.forum.ForumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ForumsServiceImpl implements ForumsService{

    @Autowired
    ForumsDao forumsDao;

    @Autowired
    DataManageService dataManageService;

    @Autowired
    ForumsEventService forumsEventService;




    @Override
    public void createForums(Long id) {

        GameLibrary gameLibrary = dataManageService.getByGidDetail(id);
        String name = "none";
        if(gameLibrary != null){
            name = gameLibrary.getName();
        }
        this.createOrUpdateForums(id, name);
    }

    @Override
    @Transactional
    public void createOrUpdateForums(Long id, String name) {

        Forums forums = forumsDao.getOne(id);
        if(forums == null){
            forums = new Forums();
            forums.setId(id);
            forums.setStatus(GameEnum.Status.VALID.getValue());
        }

        forums.setGid(id);
        forums.setName(name);
        forumsDao.saveAndFlush(forums);

        forumsEventService.onAddManageEvent(forums.getId(), "forums");


    }



    @Override
    @Transactional
    public void delete(Long id) {

        Forums forums = forumsDao.getOne(id);
        forums.setStatus(GameEnum.Status.DELETE.getValue());
        forumsDao.saveAndFlush(forums);

        forumsEventService.onDeleteEvent(id, "forums");

    }

    @Override
    public PageResp<ForumReq> forumsQuery(Specification<Forums> spec, Pageable pageable) {

        Page<Forums> forums = this.forumsDao.findAll(spec, pageable);
        PageResp<ForumReq> pagesRep = new PageResp<ForumReq>();
        for (Forums forum : forums){
            ForumReq forumRep = new ForumReq();
            forumRep.setName(forum.getName());
            forumRep.setStatus(forum.getStatus());
            forumRep.setId(forum.getId());
            forumRep.setUpdateTime(Constants.wholeDateFormat.format(forum.getUpdateTime()));
            forumRep.setPasteNum(forum.getReplyNum());
            forumRep.setFollowNum(forum.getFollowNum());
            pagesRep.getRows().add(forumRep);
        }
        pagesRep.setTotal(forums.getTotalElements());
        return pagesRep;
    }

    @Override
    public List<Forums> getForums(Long id) {
        return forumsDao.findById(id);
    }

    @Override
    @Transactional
    public void replyNumAdd(Long id) {
        Forums forums = forumsDao.getOne(id);
        if(forums != null){
            forums.setReplyNum(forums.getReplyNum() + 1);
            forumsDao.saveAndFlush(forums);
        }

    }
}
