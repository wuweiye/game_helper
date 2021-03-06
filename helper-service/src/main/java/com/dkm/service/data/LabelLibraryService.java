package com.dkm.service.data;

import com.dkm.base.Constants;
import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.dao.data.GameLabelDao;
import com.dkm.dao.data.GameLibraryDao;
import com.dkm.dao.data.LabelLibraryDao;
import com.dkm.model.data.GameLabelEntity;
import com.dkm.model.data.GameLibrary;
import com.dkm.model.data.LabelLibraryEntity;
import com.dkm.params.data.GameLabelParams;
import com.dkm.resp.data.GameLabelReq;
import com.dkm.resp.data.GameLibraryReq;
import com.dkm.resp.data.LabelLibraryReq;
import com.dkm.utils.StringUtils;
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
    GameLabelDao gameLabelDao;

    @Autowired
    LabelLibraryDao labelLibraryDao;

    @Autowired
    GameLibraryDao gameLibraryDao;


    /**
     * 查询标签库
     * @param spec
     * @param pageable
     * @return
     */
    public PageResp<LabelLibraryReq> labelQuery(Specification<LabelLibraryEntity> spec, Pageable pageable) {

        Page<LabelLibraryEntity> labelLibraryEntities = this.labelLibraryDao.findAll(spec, pageable);
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
            labelLibraryEntity = this.labelLibraryDao.findOne(req.getId());
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
        this.labelLibraryDao.save(labelLibraryEntity);


        return rep;
    }

    /**
     * 检查是否重复
     * @param req
     * @return
     */
    private boolean checkName(GameLibraryReq req) {

        LabelLibraryEntity labelLibraryEntity = this.labelLibraryDao.findByName(req.getName());
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

            entity = gameLabelDao.findOne(params.getId());
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

        gameLabelDao.saveAndFlush(entity);

        return  rep;
    }


    private GameLabelEntity getByGidAndLid(GameLabelParams req) {

        return gameLabelDao.findByGidAndLid(req.getGid(),req.getLid());
    }

    /**
     * 获取所有游戏标签
     * @param spec
     * @param pageable
     * @return
     */
    public PageResp<GameLabelReq> gameLabelQuery(Specification<GameLabelEntity> spec, Pageable pageable) {
        Page<GameLabelEntity> gameLabelEntities = this.gameLabelDao.findAll(spec, pageable);
        PageResp<GameLabelReq> pagesRep = new PageResp<GameLabelReq>();
        for(GameLabelEntity gle : gameLabelEntities){

            GameLabelReq gameLabelReq = new GameLabelReq();
            gameLabelReq.setGid(gle.getGid());
            gameLabelReq.setId(gle.getId());
            gameLabelReq.setLid(gle.getLid());
            gameLabelReq.setStatus(gle.getStatus());

            Constants.sys(gle.getLid() +"---");
            gameLabelReq.setCreateBy(Constants.wholeDateFormat.format(gle.getCreateTime()));

            GameLibrary gameLibrary = gameLibraryDao.getOne(gle.getGid());
            if(gameLibrary!=null){
                gameLabelReq.setGameName(gameLibrary.getName());
            }

            LabelLibraryEntity labelLibraryEntity = labelLibraryDao.getOne(gle.getLid());
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

        List<LabelLibraryEntity> labelLibraryEntities = labelLibraryDao.findAll();
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

        List<String> list = new ArrayList<String>();

        List<GameLabelEntity> gameLabelEntityList = gameLabelDao.findByGid(gid);
        for (GameLabelEntity labelEntity : gameLabelEntityList){
            LabelLibraryEntity labelLibraryEntity = labelLibraryDao.getOne(labelEntity.getLid());
            list.add(labelLibraryEntity.getName());
        }

        return  list;
    }





    public void test(){

        /*try {
            Constants.sys(gameLabelDao.getLid("402881eb60303c990160303e45280003"));
        } catch (Exception e) {
            Constants.sys("1 error");
        }

        try {
            Constants.sys(gameLabelDao.getLid2("402881eb60303c990160303e45280003"));
        } catch (Exception e) {
            Constants.sys("2 error");
        }

        try {
            Constants.sys(gameLabelDao.getLid3("402881eb60303c990160303e45280003"));
        } catch (Exception e) {
            Constants.sys("3 error");
        }

        try {
            Constants.sys(gameLabelDao.getLid4("402881eb60303c990160303e45280003"));
        } catch (Exception e) {
            Constants.sys("4 error");
        }*/


    }
}
