package com.dkm.game.data.service;

import com.dkm.base.Constants;
import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.game.data.dao.GameItemsRepository;
import com.dkm.game.data.dao.GameLibraryRepository;
import com.dkm.game.data.entity.GameItemsEntity;
import com.dkm.game.data.entity.GameLibrary;
import com.dkm.game.data.myenum.GameEnum;
import com.dkm.game.data.params.GameItemsParams;
import com.dkm.game.data.req.GameItemsReq;
import com.dkm.game.data.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GameItemsService {

    @Autowired
    GameItemsRepository gameItemsRepository;

    @Autowired
    GameLibraryRepository gameLibraryRepository;


    public void save(){
        GameItemsEntity gameItemsEntity = new GameItemsEntity();
        gameItemsRepository.saveAndFlush(gameItemsEntity);
    }

    public PageResp<GameItemsReq> query(Specification<GameItemsEntity> spec, Pageable pageable) {

        PageResp<GameItemsReq> resp = new PageResp<GameItemsReq>();

        Page<GameItemsEntity> gameItemsEntities = gameItemsRepository.findAll(spec, pageable);

        for(GameItemsEntity entity : gameItemsEntities){
            GameItemsReq req = new GameItemsReq();

            req.setDesc(entity.getDescription());

            req.setGid(entity.getGid());
            req.setGameName(gameLibraryRepository.getOne(entity.getGid()).getName());
            req.setName(entity.getName());
            req.setUrlPath(entity.getUrlPath());
            req.setDesc(entity.getDescription());

            req.setCreateBy(Constants.wholeDateFormat.format(entity.getCreateTime()));
            req.setCreateBy(entity.getCreateBy());

            resp.getRows().add(req);
        }



        return resp;
    }

    public BaseResp add(GameItemsParams params) {

        BaseResp baseResp = new BaseResp();

        GameItemsEntity entity;
        if(!StringUtils.isEmpty(params.getId())){

            entity = gameItemsRepository.getOne(params.getId());
            if(entity == null){
                return new BaseResp(-1, "无此资料");
            }
            entity.setUpdateTime(new Date());

        }else {
            entity = new GameItemsEntity();
        }

        entity.setDescription(params.getDesc());
        entity.setGid(params.getGid());
        entity.setName(params.getName());
        entity.setUrlPath(params.getUrlPath());

        gameItemsRepository.saveAndFlush(entity);

        return baseResp;
    }

    public BaseResp delete(String id) {

        BaseResp baseResp = new BaseResp();
        GameItemsEntity entity = gameItemsRepository.getOne(id);
        if(entity == null){
            return new BaseResp(-1, "无此资料");
        }
        entity.setStatus(GameEnum.Status.DELETE.getValue());
        gameItemsRepository.saveAndFlush(entity);

        return baseResp;
    }
}
