package com.dkm.service.data;

import com.dkm.base.Constants;
import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.dao.data.GameAssessDao;
import com.dkm.dao.data.GameDataDao;
import com.dkm.dao.data.GameLibraryDao;
import com.dkm.model.data.GameAssessEntity;
import com.dkm.model.data.GameDataEntity;
import com.dkm.model.data.GameLibrary;

import com.dkm.myenum.GameEnum;
import com.dkm.params.data.GameLibraryParams;
import com.dkm.resp.data.GameAssessQueryReq;
import com.dkm.resp.data.GameAssessUpdateReq;
import com.dkm.resp.data.GameLibraryQueryReq;
import com.dkm.resp.data.GameLibraryReq;
import com.dkm.service.app.GameDetailService;
import com.dkm.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DataManageService {

    @Autowired
    GameLibraryDao gameLibraryDao;

    @Autowired
    GameAssessDao gameAssessRepository;

    @Autowired
    GameDataDao gameDataDao;

    @Autowired
    GameDetailService gameDetailService;

    public void save(GameLibrary gameLibrary){

        gameLibraryDao.saveAndFlush(gameLibrary);
    }


    /**
     * 查询游戏列表
     * @param spec
     * @param pageable
     * @return
     */
    public PageResp<GameLibraryQueryReq> gameLibraryQuery(Specification<GameLibrary> spec, Pageable pageable) {

        Page<GameLibrary> gameLibraries = this.gameLibraryDao.findAll(spec, pageable);

        PageResp<GameLibraryQueryReq> pagesRep = new PageResp<GameLibraryQueryReq>();
        for(GameLibrary gl : gameLibraries){

            GameLibraryQueryReq req = new GameLibraryQueryReq();
            req.setGId(gl.getId()+"");
            req.setName(gl.getName());
            req.setStatus(gl.getStatus());
            req.setUpdateTime(Constants.wholeDateFormat.format(gl.getUpdateTime()));

            GameDataEntity gameDataEntity = getGameDataEntityByGid(gl.getId());

            req.setContent(gameDataEntity.getContent());
            req.setFollowCount(gameDataEntity.getFollowCount());

            GameAssessEntity gameAssessEntity = getGameAssessByGid(gl.getId());
            req.setAssess("7.0");

            pagesRep.getRows().add(req);
        }

        pagesRep.setTotal(gameLibraries.getTotalElements());
        return pagesRep;
    }

    //新增或修改
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BaseResp addGameLibrary(GameLibraryParams params, String operator) {

        BaseResp rep = new BaseResp();
        String status = checkName(params);

        Constants.sys("-----------------------");

        GameLibrary gameLibrary;
        if(!StringUtils.isEmpty(params.getGid())){
            gameLibrary = this.gameLibraryDao.findOne(params.getGid());
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

        gameLibrary.setName(params.getName());
        gameLibrary.setStatus(params.getStatus());
        if(StringUtils.isEmpty(params.getUrlPath())){
            params.setUrlPath("none");
        }
        gameLibrary.setLogoUrl(params.getUrlPath());
        GameLibrary check = gameLibraryDao.findByName(params.getName());
        if(check!=null){
            return new BaseResp(-1, "重复的标题");
        }
        gameLibrary = this.gameLibraryDao.saveAndFlush(gameLibrary);
        if(StringUtils.isEmpty(params.getGid())){
            createRelatedInfo(gameLibrary.getId(),operator,params);
        }

        return rep;
    }

    /**
     * 创建相关表信息
     * @param id
     * @param params
     */
    private void createRelatedInfo(Long id, String operator, GameLibraryParams params) {

        GameAssessEntity assessEntity = gameAssessRepository.findOne(id);
        if(assessEntity == null){
            assessEntity = new GameAssessEntity();
            assessEntity.setGid(id);
            gameAssessRepository.saveAndFlush(assessEntity);
        }

        GameDataEntity dataEntity = gameDataDao.findOne(id);

        if(dataEntity == null){

            dataEntity = new GameDataEntity();
            dataEntity.setGid(id);
            dataEntity.setContent(params.getDesc());
            dataEntity.setDevelopStore(params.getDevelopStore());
            dataEntity.setUrlPath(params.getUrlPath());
            gameDataDao.saveAndFlush(dataEntity);
        }else{
            if(!StringUtils.isEmpty(params.getDesc())){
                dataEntity.setContent(params.getDesc());
                dataEntity.setUpdateTime(new Date());
                gameDataDao.saveAndFlush(dataEntity);
            }

        }

    }

    private String checkName(GameLibraryParams params) {

        GameLibrary gameLibrary = this.gameLibraryDao.findByName(params.getName());
        if(gameLibrary != null){
            return gameLibrary.getStatus();
        }

        return GameEnum.Status.INVALID.getValue();
    }


    private String getDataContent(Long id){

        GameDataEntity gameDataEntity = gameDataDao.findOne(id);
        if(gameDataEntity != null){
            return gameDataEntity.getContent();
        }

        return null;
    }


    private void deleteAll(Long gid){

        //删除资料
        GameDataEntity gameDataEntity = gameDataDao.findByGid(gid);
        if(gameDataEntity != null){
            gameDataEntity.setStatus(GameEnum.Status.DELETE.getValue());
            gameDataDao.saveAndFlush(gameDataEntity);
        }


        //删除评价
        GameAssessEntity gameAssessEntity = gameAssessRepository.findByGid(gid);
        if (gameAssessEntity != null){
            gameAssessEntity.setStatus(GameEnum.Status.DELETE.getValue());
            gameAssessRepository.saveAndFlush(gameAssessEntity);
        }

        //删除游戏
        GameLibrary gameLibrary = gameLibraryDao.findOne(gid);
        if(gameLibrary != null){
            gameLibrary.setStatus(GameEnum.Status.DELETE.getValue());
            gameLibraryDao.saveAndFlush(gameLibrary);
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

        PageResp<GameLibraryReq> resp = new PageResp<GameLibraryReq>();

        List<GameLibrary> gameLibraries = gameLibraryDao.findByStatus(GameEnum.Status.VALID.getValue());

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

        return gameLibraryDao.getOne(gid);

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
        return gameDataDao.findByGid(gid);
    }
}
