package com.dkm.game.data.service;

import com.dkm.base.Constants;
import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.game.data.dao.GameArticleRepository;
import com.dkm.game.data.dao.GameLabelRepository;
import com.dkm.game.data.dao.LabelLibraryRepository;
import com.dkm.game.data.entity.GameArticleEntity;
import com.dkm.game.data.entity.GameLabelEntity;
import com.dkm.game.data.entity.LabelLibraryEntity;
import com.dkm.game.data.req.GameArticleReq;
import com.dkm.game.data.req.GameLabelReq;
import com.dkm.game.data.req.GameLibraryReq;
import com.dkm.game.data.req.LabelLibraryReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GameArticleService {

    @Autowired
    GameArticleRepository gameArticleRepository;


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
