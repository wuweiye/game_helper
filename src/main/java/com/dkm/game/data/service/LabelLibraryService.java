package com.dkm.game.data.service;

import com.dkm.base.Constants;
import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.game.data.dao.GameLabelRepository;
import com.dkm.game.data.dao.GameLibraryRepository;
import com.dkm.game.data.dao.LabelLibraryRepository;
import com.dkm.game.data.entity.GameLabelEntity;
import com.dkm.game.data.entity.GameLibrary;
import com.dkm.game.data.entity.LabelLibraryEntity;
import com.dkm.game.data.params.GameLabelParams;
import com.dkm.game.data.req.GameLabelReq;
import com.dkm.game.data.req.GameLibraryReq;
import com.dkm.game.data.req.LabelLibraryReq;
import com.dkm.game.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LabelLibraryService {

    @Autowired
    GameLabelRepository gameLabelRepository;

    @Autowired
    LabelLibraryRepository labelLibraryRepository;

    @Autowired
    GameLibraryRepository gameLibraryRepository;


    /**
     * 查询标签库
     * @param spec
     * @param pageable
     * @return
     */
    public PageResp<LabelLibraryReq> labelQuery(Specification<LabelLibraryEntity> spec, Pageable pageable) {

        Page<LabelLibraryEntity> labelLibraryEntities = this.labelLibraryRepository.findAll(spec, pageable);
        PageResp<LabelLibraryReq> pagesRep = new PageResp<LabelLibraryReq>();
        for(LabelLibraryEntity lle : labelLibraryEntities){

            LabelLibraryReq req = new LabelLibraryReq();
            req.setId(lle.getId());
            req.setName(lle.getName());
            req.setStatus(lle.getStatus());
            req.setUpdateTime(Constants.wholeDateFormat.format(lle.getUpdateTime()));
            pagesRep.getRows().add(req);
        }

        pagesRep.setTotal(labelLibraryEntities.getTotalElements());
        return pagesRep;
    }


    /**
     * 增加游戏标签
     * @param req
     * @param operator
     * @return
     */
    public BaseResp addGameLibrary(GameLibraryReq req, String operator) {

        BaseResp rep = new BaseResp();
        LabelLibraryEntity labelLibraryEntity;
        if(!StringUtils.isEmpty(req.getId())){
            labelLibraryEntity = this.labelLibraryRepository.findOne(req.getId());
            if (labelLibraryEntity == null) {
                return new BaseResp(-1, "无效的 id");
            }
            labelLibraryEntity.setUpdateTime(new Date());
        }else {
            boolean result = checkName(req);
            if(result){
                return new BaseResp(-1, "重复的标题");
            }
            labelLibraryEntity = new LabelLibraryEntity();
        }

        labelLibraryEntity.setName(req.getName());
        labelLibraryEntity.setStatus(req.getStatus());
        this.labelLibraryRepository.save(labelLibraryEntity);


        return rep;
    }

    /**
     * 检查是否重复
     * @param req
     * @return
     */
    private boolean checkName(GameLibraryReq req) {

        LabelLibraryEntity labelLibraryEntity = this.labelLibraryRepository.findByName(req.getName());
        if(labelLibraryEntity != null){
            return true;
        }

        return false;
    }

    /**
     * 标签与游戏的关联保存和更新
     * @param params
     * @param operator
     * @return
     */
    public BaseResp addGameLabel(GameLabelParams params, String operator) {

        BaseResp rep = new BaseResp();


        GameLabelEntity entity;
        if(!StringUtils.isEmpty(params.getId())){

            entity = gameLabelRepository.findOne(params.getId());
            if(entity == null){
                return  new BaseResp(-1,"找不到对应Id");
            }

            entity.setUpdateTime(new Date());
            entity.setUpdateBy(operator);
        }else {
            GameLabelEntity gameLabelEntity = getByGidAndLid(params);
            if(gameLabelEntity != null){
                return  new BaseResp(-1,"此游戏标签已经存在");
            }
            entity = new GameLabelEntity();
            entity.setCreateBy(operator);
        }


        entity.setGid(params.getGid());
        entity.setLid(params.getLid());
        entity.setStatus(params.getStatus());

        gameLabelRepository.saveAndFlush(entity);

        return  rep;
    }


    private GameLabelEntity getByGidAndLid(GameLabelParams req) {

        return gameLabelRepository.findByGidAndLid(req.getGid(),req.getLid());
    }

    /**
     * 获取所有游戏标签
     * @param spec
     * @param pageable
     * @return
     */
    public PageResp<GameLabelReq> gameLabelQuery(Specification<GameLabelEntity> spec, Pageable pageable) {
        Page<GameLabelEntity> gameLabelEntities = this.gameLabelRepository.findAll(spec, pageable);
        PageResp<GameLabelReq> pagesRep = new PageResp<GameLabelReq>();
        for(GameLabelEntity gle : gameLabelEntities){

            GameLabelReq gameLabelReq = new GameLabelReq();
            gameLabelReq.setGid(gle.getGid());
            gameLabelReq.setId(gle.getId());
            gameLabelReq.setLid(gle.getLid());
            gameLabelReq.setStatus(gle.getStatus());

            Constants.sys(gle.getLid() +"---");
            gameLabelReq.setCreateBy(Constants.wholeDateFormat.format(gle.getCreateTime()));

            GameLibrary gameLibrary = gameLibraryRepository.getOne(gle.getGid());
            if(gameLibrary!=null){
                gameLabelReq.setGameName(gameLibrary.getName());
            }

            LabelLibraryEntity labelLibraryEntity = labelLibraryRepository.getOne(gle.getLid());
            if(labelLibraryEntity != null){
                gameLabelReq.setLabelName(labelLibraryEntity.getName());
            }

            pagesRep.getRows().add(gameLabelReq);

        }

        pagesRep.setTotal(gameLabelEntities.getTotalElements());
        return pagesRep;

    }



    /**
     * 获取所有标签
     * @return
     */
    public PageResp<LabelLibraryReq> getLabel() {

        PageResp<LabelLibraryReq> resp = new PageResp<LabelLibraryReq>();

        List<LabelLibraryEntity> labelLibraryEntities = labelLibraryRepository.findAll();
        for(LabelLibraryEntity entity :labelLibraryEntities){

            LabelLibraryReq labelLibraryReq = new LabelLibraryReq();
            labelLibraryReq.setName(entity.getName());
            labelLibraryReq.setId(entity.getId());
            resp.getRows().add(labelLibraryReq);
        }

        return resp;
    }


    /**
     * 根据gid 获取一个游戏所有标签
     * @param gid
     * @return List<String>
     */
    public List<String> getByGidLabel(Long gid){

        List<String> list = new ArrayList<>();

        List<GameLabelEntity> gameLabelEntityList = gameLabelRepository.findByGid(gid);
        for (GameLabelEntity labelEntity : gameLabelEntityList){
            LabelLibraryEntity labelLibraryEntity = labelLibraryRepository.getOne(labelEntity.getLid());
            list.add(labelLibraryEntity.getName());
        }

        return  list;
    }





    public void test(){

        /*try {
            Constants.sys(gameLabelRepository.getLid("402881eb60303c990160303e45280003"));
        } catch (Exception e) {
            Constants.sys("1 error");
        }

        try {
            Constants.sys(gameLabelRepository.getLid2("402881eb60303c990160303e45280003"));
        } catch (Exception e) {
            Constants.sys("2 error");
        }

        try {
            Constants.sys(gameLabelRepository.getLid3("402881eb60303c990160303e45280003"));
        } catch (Exception e) {
            Constants.sys("3 error");
        }

        try {
            Constants.sys(gameLabelRepository.getLid4("402881eb60303c990160303e45280003"));
        } catch (Exception e) {
            Constants.sys("4 error");
        }*/


    }
}
