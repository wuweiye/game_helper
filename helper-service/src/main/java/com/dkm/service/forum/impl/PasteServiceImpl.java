package com.dkm.service.forum.impl;

import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.dao.forum.PasteDao;

import com.dkm.event.ForumsEventService;
import com.dkm.model.forum.Paste;
import com.dkm.model.forum.ReplyMain;
import com.dkm.model.user.UserEntity;
import com.dkm.myenum.GameEnum;
import com.dkm.params.forum.PasteParams;
import com.dkm.resp.app.PasteDetailReq;
import com.dkm.resp.app.PasteReq;
import com.dkm.service.forum.PasteService;
import com.dkm.service.forum.ReplyMainService;
import com.dkm.service.user.UserService;
import com.dkm.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @Autowired
    UserService userService;

    @Autowired
    ReplyMainService replyMainService;

    @Override
    public BaseResp save(PasteParams params) {
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


        return new BaseResp();
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


    /**
     * 查询发帖数
     * @param spec
     * @param pageable
     * @return
     */
    @Override
    public PageResp<PasteReq> replyMainQuery(Specification<Paste> spec, Pageable pageable) {

        PageResp<PasteReq> resp = new PageResp<PasteReq>();
        Page<Paste> pastes = pasteDao.findAll(spec, pageable);

        for(Paste paste : pastes){
            PasteReq req = new PasteReq();
            req.setId(paste.getId());
            req.setTitle(paste.getTitle());
            req.setContent(paste.getContent());
            req.setFollowNum(paste.getGoodNum());
            req.setReplyNum(paste.getReplyNum());

            UserEntity userEntity = userService.getUser(paste.getUid());
            req.setAuthor(userEntity.getUserName());

            resp.getRows().add(req);
        }

        resp.setTotal(pastes.getTotalElements());

        return resp;
    }



    @Override
    public Paste getPasteById(Long id) {
        return pasteDao.getOne(id);
    }




    @Override
    public PageResp<PasteDetailReq> getPasteDetail(Long id) {

        PageResp<PasteDetailReq> resp = new PageResp<PasteDetailReq>();

        Paste paste = pasteDao.getOne(id);
        if(paste == null){
            return resp;
        }

        List<ReplyMain> replyMains = replyMainService.getReplays(paste.getId());

        for(ReplyMain replyMain : replyMains){
            PasteDetailReq pasteReq = new PasteDetailReq();
            UserEntity userEntity = userService.getUser(replyMain.getUid());
            pasteReq.setAuthor(userEntity.getUserName());
            pasteReq.setContent(replyMain.getContent());
            pasteReq.setReplyNum(replyMain.getReplyNum());

        }

        return null;
    }
}
