package com.dkm.service.article;


import com.dkm.base.Constants;
import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.dao.data.GameLibraryRepository;
import com.dkm.dao.game.GameArticleDao;
import com.dkm.model.game.GameArticleEntity;
import com.dkm.myenum.GameEnum;
import com.dkm.params.article.GameArticleParams;
import com.dkm.resp.article.GameArticleReq;
import com.dkm.service.base.BaseService;
import com.dkm.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GameArticleService extends BaseService {

    @Autowired
    GameArticleDao gameArticleRepository;

    @Autowired
    GameLibraryRepository gameLibraryRepository;


    /**
     * 查询展示  前后台
     * @param spec
     * @param pageable
     * @return
     */
    public PageResp<GameArticleReq> gameArticleQuery(Specification<GameArticleEntity> spec, Pageable pageable) {

        Page<GameArticleEntity> gameArticleEntities = gameArticleRepository.findAll(spec,pageable);
        PageResp<GameArticleReq> resp = new PageResp<GameArticleReq>();

        for(GameArticleEntity ga : gameArticleEntities){
            GameArticleReq gameArticleReq = new GameArticleReq();
            gameArticleReq.setId(ga.getId());
            gameArticleReq.setGid(ga.getGid());
            gameArticleReq.setStatus(ga.getStatus());
            gameArticleReq.setGameName(gameLibraryRepository.getOne(ga.getGid()).getName());
            gameArticleReq.setContent(ga.getContent());
            gameArticleReq.setCreateBy(ga.getCreateBy());
            gameArticleReq.setCreateTime(Constants.wholeDateFormat.format(ga.getCreateTime()));
            gameArticleReq.setIsShow(ga.getIsShow());
            gameArticleReq.setTitle(ga.getTitle());
            gameArticleReq.setUpdateTime(Constants.wholeDateFormat.format(ga.getUpdateTime()));
            resp.getRows().add(gameArticleReq);
        }

        resp.setTotal(gameArticleEntities.getTotalElements());

        return resp;
    }


    /**
     * 添加修改文章 后台
     * @param params
     * @param operator
     * @return
     */
    public BaseResp add(GameArticleParams params, String operator) {

        BaseResp baseResp = new BaseResp();

        GameArticleEntity entity;

        if(!StringUtils.isEmpty(params.getId())){

            entity = gameArticleRepository.getOne(params.getId());
            entity.setUpdateTime(new Date());

        }else{

            entity= new GameArticleEntity();
            entity.setCreateBy(operator);
        }


        entity.setGid(params.getGid());
        entity.setStatus(params.getStatus());
        entity.setIsShow(params.getIsShow());
        entity.setContent(params.getContent());
        entity.setTitle(params.getTitle());
        entity.setUpdateBy(operator);

        gameArticleRepository.saveAndFlush(entity);

        return baseResp;

    }

    /**
     * delete 后台
     * @param id
     * @param operator
     * @return
     */
    public BaseResp detele(Long id, String operator) {
        BaseResp baseResp = new BaseResp();

        GameArticleEntity entity = gameArticleRepository.getOne(id);
        if(entity == null){
            return new BaseResp(-1,"不存在的文章");
        }

        entity.setStatus(GameEnum.Status.DELETE.getValue());
        gameArticleRepository.saveAndFlush(entity);

        return baseResp;
    }
}
