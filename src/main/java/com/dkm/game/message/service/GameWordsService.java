package com.dkm.game.message.service;

import com.dkm.base.Constants;
import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.game.message.dao.GameReplyRepository;
import com.dkm.game.message.dao.GameWordsRepository;
import com.dkm.game.message.entity.GameReplyEntity;
import com.dkm.game.message.entity.GameWordsEntity;
import com.dkm.game.message.req.GameReplyReq;
import com.dkm.game.message.req.GameWordsQueryReq;
import com.dkm.game.message.req.GameWordsReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
public class GameWordsService {

    @Autowired
    GameWordsRepository gameWordsRepository;

    @Autowired
    GameReplyRepository gameReplyRepository;


    public PageResp<GameWordsQueryReq> gameWordsReqPageQuery(Specification<GameWordsEntity> spec, Pageable pageable){

        Page<GameWordsEntity> gameWordsEntities = gameWordsRepository.findAll(spec,pageable);
        PageResp<GameWordsQueryReq> pageResp = new PageResp<GameWordsQueryReq>();

        for(GameWordsEntity entity : gameWordsEntities){

            GameWordsQueryReq req = new GameWordsQueryReq();
            req.setContent(entity.getContent());
            req.setCreateBy(entity.getCreateBy());
            req.setCreateTime(Constants.wholeDateFormat.format(entity.getCreateTime()));
            req.setGoodNum(entity.getGoodNum());
            req.setIsShow(entity.getIsShow());
            req.setReplyNum(entity.getReplyNum());
            req.setStatus(entity.getStatus());
            req.setUid(entity.getUid());
            //TODO : 回复留言信息获取

            pageResp.getRows().add(req);

        }



        return pageResp;
    }





    public BaseResp addWords(GameWordsReq req){

        BaseResp resp = new BaseResp();
        GameWordsEntity gameWordsEntity = new GameWordsEntity();
        gameWordsEntity.setContent(req.getContent());
        gameWordsEntity.setGid(req.getGid());
        gameWordsEntity.setUid(req.getUid());

        gameWordsRepository.saveAndFlush(gameWordsEntity);
        return  resp;
    }

    public BaseResp addReplay(GameReplyReq req){

        BaseResp resp = new BaseResp();

        GameReplyEntity gameReplyEntity = new GameReplyEntity();
        gameReplyEntity.setContent(req.getContent());
        gameReplyEntity.setGid(req.getGid());
        gameReplyEntity.setWid(req.getWid());
        gameReplyEntity.setUid(req.getUid());

        gameReplyRepository.saveAndFlush(gameReplyEntity);

        return  resp;
    }




}
