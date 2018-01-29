package com.dkm.service.forum.impl;

import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.dao.forum.ReplyBranchDao;
import com.dkm.event.ForumsEventService;
import com.dkm.model.forum.ReplyBranch;
import com.dkm.myenum.GameEnum;
import com.dkm.params.forum.ReplyParams;
import com.dkm.service.forum.ReplyBranchService;
import com.dkm.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyBranchServiceImpl implements ReplyBranchService {


    @Autowired
    ReplyBranchDao replyBranchDao;

    @Autowired
    ForumsEventService forumsEventService;

    @Override
    public void save(ReplyParams params) {

        ReplyBranch replyBranch;

        if(StringUtils.isEmpty(params.getId())){
            replyBranch = new ReplyBranch();

        }else {
            replyBranch = replyBranchDao.getOne(params.getId());
        }

        replyBranch.setUid(params.getUid());
        replyBranch.setContent(params.getContent());
        replyBranch.setPid(params.getPid());

        replyBranchDao.saveAndFlush(replyBranch);

        forumsEventService.onAddManageEvent(replyBranch.getId(), "replyBranch");

    }

    @Override
    public BaseResp delete(Long id) {
        BaseResp rep = new BaseResp();
        ReplyBranch replyBranch = replyBranchDao.getOne(id);

        if(replyBranch == null){
            return new BaseResp(-1, "无效的 id");
        }

        replyBranch.setStatus(GameEnum.Status.DELETE.getValue());

        return rep;

    }

    @Override
    public void deleteAll(Long rmId) {

        List<ReplyBranch> replyBranches = replyBranchDao.findByParent(rmId);
        for(ReplyBranch replyBranch : replyBranches){

            replyBranch.setStatus(GameEnum.Status.DELETE.getValue());
            replyBranchDao.saveAndFlush(replyBranch);
        }

    }
}
