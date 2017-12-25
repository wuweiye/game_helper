package com.dkm.game.data.service;

import com.dkm.base.Constants;
import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.game.data.dao.GameAssessRepository;
import com.dkm.game.data.dao.GameDataRepository;
import com.dkm.game.data.entity.GameAssessEntity;
import com.dkm.game.data.entity.GameDataEntity;
import com.dkm.game.data.myenum.GameEnum;
import com.dkm.game.data.req.*;
import com.dkm.game.data.dao.GameLibraryRepository;
import com.dkm.game.data.entity.GameLibrary;
import com.dkm.game.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DataManageService {

    @Autowired
    GameLibraryRepository gameLibraryRepository;

    @Autowired
    GameAssessRepository gameAssessRepository;

    @Autowired
    GameDataRepository gameDataRepository;

    public void save(GameLibrary gameLibrary){

        gameLibraryRepository.saveAndFlush(gameLibrary);
    }


    public PageResp<GameLibraryQueryReq> gameLibraryQuery(Specification<GameLibrary> spec, Pageable pageable) {

        Page<GameLibrary> gameLibraries = this.gameLibraryRepository.findAll(spec, pageable);

        PageResp<GameLibraryQueryReq> pagesRep = new PageResp<GameLibraryQueryReq>();
        for(GameLibrary gl : gameLibraries){

            GameLibraryQueryReq req = new GameLibraryQueryReq();
            req.setGId(gl.getId()+"");
            req.setName(gl.getName());
            req.setStatus(gl.getStatus());
            req.setUpdateTime(Constants.wholeDateFormat.format(gl.getUpdateTime()));
            //获取游戏资料
            req.setContent(getDataContent(gl.getId()));
            pagesRep.getRows().add(req);
        }

        pagesRep.setTotal(gameLibraries.getTotalElements());
        return pagesRep;
    }

    //新增或修改
    @Transactional
    public BaseResp addGameLibrary(GameLibraryReq req, String operator) {

        BaseResp rep = new BaseResp();
        String status = checkName(req);

        GameLibrary gameLibrary;
        if(!StringUtils.isEmpty(req.getGid())){
            gameLibrary = this.gameLibraryRepository.findOne(req.getGid());
            if (gameLibrary == null) {
                return new BaseResp(-1, "无效的 id");
            }
            gameLibrary.setUpdateTime(new Date());
        }else {
            if(status != GameEnum.Status.INVALID.getValue()){

                if(status == GameEnum.Status.DELETE.getValue()){
                    return new BaseResp(-1, "已经删除的标题，恢复待定");
                }
                return new BaseResp(-1, "重复的标题");
            }
            gameLibrary = new GameLibrary();
        }

        gameLibrary.setName(req.getName());
        gameLibrary.setStatus(req.getStatus());
        gameLibrary = this.gameLibraryRepository.saveAndFlush(gameLibrary);
        if(StringUtils.isEmpty(req.getGid())){
            createRelatedInfo(gameLibrary.getId(),operator,req);
        }

        return rep;
    }

    /**
     * 创建相关表信息
     * @param id
     * @param req
     */
    private void createRelatedInfo(Long id, String operator, GameLibraryReq req) {

        GameAssessEntity assessEntity = gameAssessRepository.findOne(id);
        if(assessEntity == null){
            assessEntity = new GameAssessEntity();
            assessEntity.setGid(id);
            gameAssessRepository.saveAndFlush(assessEntity);
        }

        GameDataEntity dataEntity = gameDataRepository.findOne(id);

        if(dataEntity == null){

            dataEntity = new GameDataEntity();
            dataEntity.setGid(id);
            dataEntity.setContent(req.getContent());
            gameDataRepository.saveAndFlush(dataEntity);
        }else{
            if(!StringUtils.isEmpty(req.getContent())){
                dataEntity.setContent(req.getContent());
                dataEntity.setUpdateTime(new Date());
                gameDataRepository.saveAndFlush(dataEntity);
            }

        }

    }

    private String checkName(GameLibraryReq req) {

        GameLibrary gameLibrary = this.gameLibraryRepository.findByName(req.getName());
        if(gameLibrary != null){
            return gameLibrary.getStatus();
        }

        return GameEnum.Status.INVALID.getValue();
    }


    private String getDataContent(Long id){

        GameDataEntity gameDataEntity = gameDataRepository.findOne(id);
        if(gameDataEntity != null){
            return gameDataEntity.getContent();
        }

        return null;
    }


    private void deleteAll(Long gid){

        //删除资料
        GameDataEntity gameDataEntity = gameDataRepository.findByGid(gid);
        if(gameDataEntity != null){
            gameDataEntity.setStatus(GameEnum.Status.DELETE.getValue());
            gameDataRepository.saveAndFlush(gameDataEntity);
        }


        //删除评价
        GameAssessEntity gameAssessEntity = gameAssessRepository.findByGid(gid);
        if (gameAssessEntity != null){
            gameAssessEntity.setStatus(GameEnum.Status.DELETE.getValue());
            gameAssessRepository.saveAndFlush(gameAssessEntity);
        }

        //删除游戏
        GameLibrary gameLibrary = gameLibraryRepository.findOne(gid);
        if(gameLibrary != null){
            gameLibrary.setStatus(GameEnum.Status.DELETE.getValue());
            gameLibraryRepository.saveAndFlush(gameLibrary);
        }


    }


    /**
     * 修改评价 - api
     * @param req
     * @param operator
     * @return
     */
    public BaseResp updateStar(GameAssessUpdateReq req, String operator) {

        BaseResp resp = new BaseResp();

        GameAssessEntity entity = gameAssessRepository.getOne(req.getId());

        //TODO : 后期转为枚举判断 去掉if else
        if(req.getStar().equals(GameEnum.Star.ONE.getValue())){
            entity.setOneStarNum(entity.getOneStarNum()+1);
        }else if(req.getStar().equals(GameEnum.Star.TWO.getValue())){

            entity.setTwoStarNum(entity.getTwoStarNum()+1);
        }else if(req.getStar().equals(GameEnum.Star.THERE.getValue())){

            entity.setThereStarNum(entity.getThereStarNum()+1);
        }else if(req.getStar().equals(GameEnum.Star.FOUR.getValue())){

            entity.setFourStarNum(entity.getFourStarNum()+1);
        }else if(req.getStar().equals(GameEnum.Star.FIVE.getValue())){

            entity.setFiveStarNum(entity.getFiveStarNum()+1);
        }

        gameAssessRepository.saveAndFlush(entity);
        return resp;

    }


    public GameAssessQueryReq gameAssessQuery(Long id){

        GameAssessQueryReq gameAssessQueryReq = new GameAssessQueryReq();
        GameAssessEntity entity = gameAssessRepository.getOne(id);
        if(entity!= null){

            gameAssessQueryReq.setOneStar(entity.getScale(entity.getOneStarNum()));
            gameAssessQueryReq.setTwoStar(entity.getScale(entity.getTwoStarNum()));
            gameAssessQueryReq.setThereStsr(entity.getScale(entity.getThereStarNum()));
            gameAssessQueryReq.setFourStar(entity.getScale(entity.getFourStarNum()));
            gameAssessQueryReq.setFiveStar(entity.getScale(entity.getFiveStarNum()));

        }

        return gameAssessQueryReq;

    }

    public PageResp<GameLibraryReq> getGame() {

        PageResp<GameLibraryReq> resp = new PageResp<>();

        List<GameLibrary> gameLibraries = gameLibraryRepository.findByStatus(GameEnum.Status.VALID.getValue());

        for(GameLibrary library : gameLibraries){

            GameLibraryReq req = new GameLibraryReq();
            req.setId(library.getId());
            req.setName(library.getName());
            resp.getRows().add(req);
        }


        resp.setTotal(gameLibraries.size());

        return resp;
    }


    /**
     * 根据gid 获取游戏
     * @param gid
     */
    public GameLibrary getByGidDetail(Long gid){

        return gameLibraryRepository.getOne(gid);

    }

    /**
     * 根据gid 获取游戏评价
     * @param gid
     */
    public  GameAssessEntity  getGameAssessByGid(Long gid){
        return gameAssessRepository.findByGid(gid);
    }


    /**
     * 根据gid 获取游戏资料
     * @param gid
     */
    public GameDataEntity getGameDataEntityByGid(Long gid){
        return gameDataRepository.findByGid(gid);
    }
}
