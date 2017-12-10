package com.dkm.game.message.service;

import com.dkm.base.Constants;
import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.game.message.dao.GameReplyRepository;
import com.dkm.game.message.dao.GameWordsRepository;
import com.dkm.game.message.entity.GameReplyEntity;
import com.dkm.game.message.entity.GameWordsEntity;
import com.dkm.game.message.params.GameReplyAddParams;
import com.dkm.game.message.params.GameWordsAddParams;
import com.dkm.game.message.req.GameReplyReq;
import com.dkm.game.message.req.GameWordsQueryReq;
import com.dkm.game.message.req.GameWordsReq;
import com.dkm.game.user.dao.UserDao;
import com.dkm.game.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class GameWordsService {

    @Autowired
    GameWordsRepository gameWordsRepository;

    @Autowired
    GameReplyRepository gameReplyRepository;

    @Autowired
    UserDao userDao;


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


    @Transactional
    public BaseResp addWords(GameWordsAddParams params){


        UserEntity userEntity = userDao.findOne(params.getUid());
        BaseResp resp = new BaseResp();

        if(userEntity != null){

            GameWordsEntity gameWordsEntity = new GameWordsEntity();
            gameWordsEntity.setContent(params.getContent());
            gameWordsEntity.setGid(params.getGid());
            gameWordsEntity.setUid(params.getUid());
            gameWordsEntity.setCreateBy(userEntity.getUserName());
            gameWordsRepository.saveAndFlush(gameWordsEntity);
        }

        return  resp;
    }

    @Transactional
    public BaseResp addReplay(GameReplyAddParams params){

        UserEntity userEntity = userDao.findOne(params.getUid());

        BaseResp resp = new BaseResp();

        if(userEntity != null){

            GameReplyEntity gameReplyEntity = new GameReplyEntity();
            gameReplyEntity.setContent(params.getContent());
            gameReplyEntity.setGid(params.getGid());
            gameReplyEntity.setWid(params.getWid());
            gameReplyEntity.setUid(params.getUid());
            gameReplyEntity.setCreateBy(userEntity.getUserName());
            gameReplyRepository.saveAndFlush(gameReplyEntity);

        }
        return  resp;

    }




}
