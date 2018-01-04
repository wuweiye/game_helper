package com.dkm.service.data;

import com.dkm.base.Constants;
import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.dao.data.GameItemsRepository;
import com.dkm.dao.data.GameLibraryRepository;
import com.dkm.model.data.GameItemsEntity;
import com.dkm.myenum.GameEnum;
import com.dkm.params.data.GameItemsParams;
import com.dkm.resp.data.GameItemsReq;
import com.dkm.utils.StringUtils;
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

            req.setId(entity.getId());

            req.setDesc(entity.getDescription());

            req.setGid(entity.getGid());
            req.setGameName(gameLibraryRepository.getOne(entity.getGid()).getName());
            req.setName(entity.getName());
            req.setUrlPath(entity.getUrlPath());
            req.setDesc(entity.getDescription());

            req.setStatus(entity.getStatus());

            req.setCreateBy(Constants.wholeDateFormat.format(entity.getCreateTime()));
            req.setCreateBy(entity.getCreateBy());

            resp.getRows().add(req);
        }


        resp.setTotal(gameItemsEntities.getTotalElements());

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
        if(!StringUtils.isEmpty(params.getUrlPath())){
            entity.setUrlPath(params.getUrlPath());
        }


        gameItemsRepository.saveAndFlush(entity);

        return baseResp;
    }

    public BaseResp delete(Long id) {

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
