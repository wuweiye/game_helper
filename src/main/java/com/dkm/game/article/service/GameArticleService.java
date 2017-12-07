package com.dkm.game.article.service;

import com.dkm.base.Constants;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.game.article.dao.GameArticleDao;
import com.dkm.game.article.entity.GameArticleEntity;
import com.dkm.game.data.req.GameArticleReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class GameArticleService {

    @Autowired
    GameArticleDao gameArticleRepository;


    public PageResp<GameArticleReq> gameArticleQuery(Specification<GameArticleEntity> spec, Pageable pageable) {

        Page<GameArticleEntity> gameArticleEntities = gameArticleRepository.findAll(spec,pageable);
        PageResp<GameArticleReq> resp = new PageResp<GameArticleReq>();

        for(GameArticleEntity ga : gameArticleEntities){
            GameArticleReq gameArticleReq = new GameArticleReq();
            gameArticleReq.setId(ga.getId());
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
}
