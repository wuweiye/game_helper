package com.dkm.service.forum.impl;

import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.dao.forum.PasteDao;

import com.dkm.event.ForumsEventService;
import com.dkm.model.forum.Paste;
import com.dkm.myenum.GameEnum;
import com.dkm.params.forum.PasteParams;
import com.dkm.service.forum.PasteService;
import com.dkm.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasteServiceImpl implements PasteService{

    @Autowired
    PasteDao pasteDao;

    @Autowired
    ForumsEventService forumsEventService;

    @Override
    public void save(PasteParams params) {
        Paste paste;

        if(StringUtils.isEmpty(params.getUid())){
            paste = new Paste();
        }else {
            paste = pasteDao.getOne(params.getId());
        }

        paste.setContent(params.getContent());
        paste.setGid(params.getGid());
        paste.setUid(params.getUid());
        paste.setFid(params.getFid());
        paste.setTitle(params.getTitle());


        pasteDao.saveAndFlush(paste);

        forumsEventService.onAddManageEvent(paste.getId(),"paste");


    }

    @Override
    public BaseResp delete(Long id) {

        BaseResp baseResp = new BaseResp();

        Paste paste = pasteDao.getOne(id);
        if(paste == null){
            return new BaseResp(-1, "无效的 id");
        }

        paste.setStatus(GameEnum.Status.DELETE.getValue());
        pasteDao.saveAndFlush(paste);

        forumsEventService.onDeleteEvent(id, "paste");


        return baseResp;
    }


    @Override
    public void deleteAll(Long id) {

        Paste paste = pasteDao.getOne(id);
        if(paste != null){
            paste.setStatus(GameEnum.Status.DELETE.getValue());
            pasteDao.saveAndFlush(paste);

        }

    }

    @Override
    public List<Paste> getPastes(Long fId) {

       return pasteDao.findByFid(fId);

    }



    @Override
    public PageResp<Paste> replyMainQuery(Specification<Paste> spec, Pageable pageable) {
        return null;
    }
}
