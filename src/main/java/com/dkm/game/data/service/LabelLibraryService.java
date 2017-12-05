package com.dkm.game.data.service;

import com.dkm.base.Constants;
import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.game.data.dao.GameLabelRepository;
import com.dkm.game.data.dao.LabelLibraryRepository;
import com.dkm.game.data.entity.GameLabelEntity;
import com.dkm.game.data.entity.GameLibrary;
import com.dkm.game.data.entity.LabelLibraryEntity;
import com.dkm.game.data.req.GameLabelReq;
import com.dkm.game.data.req.GameLibraryQueryReq;
import com.dkm.game.data.req.GameLibraryReq;
import com.dkm.game.data.req.LabelLibraryReq;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class LabelLibraryService {

    @Autowired
    GameLabelRepository gameLabelRepository;

    @Autowired
    LabelLibraryRepository labelLibraryRepository;


    public PageResp<LabelLibraryReq> labelQuery(Specification<LabelLibraryEntity> spec, Pageable pageable) {

        Page<LabelLibraryEntity> labelLibraryEntities = this.labelLibraryRepository.findAll(spec, pageable);
        PageResp<LabelLibraryReq> pagesRep = new PageResp<LabelLibraryReq>();
        for(LabelLibraryEntity lle : labelLibraryEntities){

            LabelLibraryReq req = new LabelLibraryReq();
            req.setGid(lle.getGid());
            req.setName(lle.getName());
            req.setStatus(String.valueOf(lle.isStatus()));
            req.setUpdateTime(Constants.wholeDateFormat.format(lle.getUpdateTime()));
            pagesRep.getRows().add(req);
        }

        pagesRep.setTotal(labelLibraryEntities.getTotalElements());
        return pagesRep;
    }



    public BaseResp addGameLibrary(GameLibraryReq req, String operator) {

        BaseResp rep = new BaseResp();
        boolean result = checkName(req);
        if(result){
            return new BaseResp(-1, "重复的标题");
        }
        LabelLibraryEntity labelLibraryEntity;
        if(req.getGid() != null && !"".equals(req.getGid())){
            labelLibraryEntity = this.labelLibraryRepository.findOne(req.getGid());
            if (labelLibraryEntity == null) {
                return new BaseResp(-1, "无效的 id");
            }
            labelLibraryEntity.setUpdateTime(new Date());
        }else {
            labelLibraryEntity = new LabelLibraryEntity();
        }

        labelLibraryEntity.setName(req.getName());
        labelLibraryEntity.setStatus(Boolean.valueOf(req.getStatus()));
        this.labelLibraryRepository.save(labelLibraryEntity);


        return rep;
    }

    private boolean checkName(GameLibraryReq req) {

        LabelLibraryEntity labelLibraryEntity = this.labelLibraryRepository.findByName(req.getName());
        if(labelLibraryEntity != null){
            return true;
        }

        return false;
    }

    /**
     * 标签与游戏的关联保存和更新
     * @param req
     * @param operator
     * @return
     */
    public BaseResp addGameLabel(GameLabelReq req, String operator) {

        BaseResp rep = new BaseResp();

        GameLabelEntity gameLabelEntity = getByGidAndLid(req);
        if(gameLabelEntity != null){
            return  new BaseResp(-1,"此游戏标签已经存在");
        }
        GameLabelEntity entity;
        if(req.getId() != null && req.getId()!=""){

            entity = gameLabelRepository.findOne(req.getId());
            if(entity == null){
                return  new BaseResp(-1,"找不到对应Id");
            }

            entity.setUpdateTime(new Date());
            entity.setUpdateBy(operator);
        }else {
            entity = new GameLabelEntity();
            entity.setCreateBy(operator);
        }


        entity.setGid(req.getGid());
        entity.setLid(req.getLid());
        entity.setStatus(Boolean.parseBoolean(req.getStatus()));

        gameLabelRepository.saveAndFlush(entity);

        return  rep;
    }

    private GameLabelEntity getByGidAndLid(GameLabelReq req) {

        return gameLabelRepository.findByGidAndLid(req.getGid(),req.getLid());
    }

    public PageResp<GameLabelReq> gameLabelQuery(Specification<GameLabelEntity> spec, Pageable pageable) {
        Page<GameLabelEntity> gameLabelEntities = this.gameLabelRepository.findAll(spec, pageable);
        PageResp<GameLabelReq> pagesRep = new PageResp<GameLabelReq>();
        for(GameLabelEntity gle : gameLabelEntities){

            GameLabelReq gameLabelReq = new GameLabelReq();
            gameLabelReq.setGid(gle.getGid());
            gameLabelReq.setId(gle.getId());
            gameLabelReq.setLid(gle.getLid());
            gameLabelReq.setStatus(String.valueOf(gle.isStatus()));

        }

        pagesRep.setTotal(gameLabelEntities.getTotalElements());
        return pagesRep;

    }
}
