package com.dkm.game.data.service;

import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.game.data.dao.GameLabelRepository;
import com.dkm.game.data.dao.LabelLibraryRepository;
import com.dkm.game.data.entity.GameLabelEntity;
import com.dkm.game.data.entity.GameLibrary;
import com.dkm.game.data.entity.LabelLibraryEntity;
import com.dkm.game.data.req.GameLibraryQueryReq;
import com.dkm.game.data.req.GameLibraryReq;
import com.dkm.game.data.req.LabelLibraryReq;
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


    public void save(){
        try {
            GameLabelEntity gameLabelEntity = new GameLabelEntity();
            gameLabelRepository.save(gameLabelEntity);
        } catch (Exception e) {
            System.out.println("11111");
        }

        try {
            LabelLibraryEntity labelLibraryEntity = new LabelLibraryEntity();
            labelLibraryRepository.save(labelLibraryEntity);
        } catch (Exception e) {
            System.out.println("22222");
        }

    }

    public PageResp<LabelLibraryReq> labelQuery(Specification<LabelLibraryEntity> spec, Pageable pageable) {

        Page<LabelLibraryEntity> labelLibraryEntities = this.labelLibraryRepository.findAll(spec, pageable);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PageResp<LabelLibraryReq> pagesRep = new PageResp<LabelLibraryReq>();
        for(LabelLibraryEntity lle : labelLibraryEntities){

            LabelLibraryReq req = new LabelLibraryReq();
            req.setGid(lle.getGid());
            req.setName(lle.getName());
            req.setStatus(String.valueOf(lle.isStatus()));
            req.setUpdateTime(simpleDateFormat.format(lle.getUpdateTime()));
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
}
