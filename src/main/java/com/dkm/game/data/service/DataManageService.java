package com.dkm.game.data.service;

import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.game.data.controller.GameLibraryQueryReq;
import com.dkm.game.data.controller.GameLibraryReq;
import com.dkm.game.data.dao.GameLibraryRepository;
import com.dkm.game.data.entity.GameLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DataManageService {

    @Autowired
    GameLibraryRepository gameLibraryRepository;

    public void save(GameLibrary gameLibrary){

        gameLibraryRepository.saveAndFlush(gameLibrary);
    }


    public  void update(GameLibrary gameLibrary){
        GameLibrary gameLibrary1 = gameLibraryRepository.findOne(gameLibrary.getGId());
    }

    public void print(){

        /*List<GameLibrary> gameLibraries = gameLibraryRepository.findByName("test");
        System.out.println(gameLibraries.size());*/

    }

    public PageResp<GameLibraryQueryReq> gameLibraryQuery(Specification<GameLibrary> spec, Pageable pageable) {

        Page<GameLibrary> gameLibraries = this.gameLibraryRepository.findAll(spec, pageable);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        PageResp<GameLibraryQueryReq> pagesRep = new PageResp<GameLibraryQueryReq>();
        for(GameLibrary gl : gameLibraries){

            GameLibraryQueryReq req = new GameLibraryQueryReq();
            req.setGId(gl.getGId());
            req.setName(gl.getName());
            req.setStatus(gl.isStatus());
            req.setUpdateTime(simpleDateFormat.format(gl.getUpdateTime()));
            pagesRep.getRows().add(req);
        }

        pagesRep.setTotal(gameLibraries.getTotalElements());
        return pagesRep;
    }

    //新增或修改
    public BaseResp addGameLibrary(GameLibraryReq req, String operator) {

        BaseResp rep = new BaseResp();
        boolean result = checkName(req);
        if(result){
            return new BaseResp(-1, "重复的标题");
        }
        GameLibrary gameLibrary;
        if(req.getGid() != null && !"".equals(req.getGid())){
            gameLibrary = this.gameLibraryRepository.findOne(req.getGid());
            if (gameLibrary == null) {
                return new BaseResp(-1, "无效的 id");
            }
            gameLibrary.setUpdateTime(new Date());
        }else {
            gameLibrary = new GameLibrary();
        }

        gameLibrary.setName(req.getName());
        gameLibrary.setStatus(Boolean.valueOf(req.getStatus()));
        this.gameLibraryRepository.save(gameLibrary);


        return rep;
    }

    private boolean checkName(GameLibraryReq req) {

        GameLibrary gameLibrary = this.gameLibraryRepository.findByName(req.getName());
        if(gameLibrary != null){
            return true;
        }

        return false;
    }


}
