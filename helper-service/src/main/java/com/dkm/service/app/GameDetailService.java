package com.dkm.service.app;

import com.dkm.model.data.GameAssessEntity;
import com.dkm.model.data.GameDataEntity;
import com.dkm.model.data.GameLibrary;
import com.dkm.params.data.GameDetailParams;
import com.dkm.resp.app.GameDetailReq;
import com.dkm.service.data.DataManageService;
import com.dkm.service.data.LabelLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GameDetailService {

    @Autowired
    DataManageService dataManageService;

    @Autowired
    LabelLibraryService labelLibraryService;


    /**
     * 获取游戏详情页面展示信息
     * @param params
     * @return
     */

    public GameDetailReq getDetail(GameDetailParams params){

        GameDetailReq req  = new GameDetailReq();

        req.setId(params.getGid());

        GameLibrary gameLibrary = dataManageService.getByGidDetail(params.getGid());
        GameDataEntity gameDataEntity = dataManageService.getGameDataEntityByGid(params.getGid());
        GameAssessEntity gameAssessEntity = dataManageService.getGameAssessByGid(params.getGid());

        req.setGameName(gameLibrary.getName());
        req.setStatus(gameLibrary.getStatus());
        req.setFollowCount(gameDataEntity.getFollowCount());
        req.setDevelopStore(gameDataEntity.getDevelopStore());
        if(gameDataEntity.getUrlPath().equals("none")){
            req.setUrlPath("http://bpic.588ku.com/element_origin_min_pic/16/10/30/528aa13209e86d5d9839890967a6b9c1.jpg");
        }else {
            req.setUrlPath(gameDataEntity.getUrlPath());
        }


        //评价
        req.setOneStarNum(gameAssessEntity.getOneStarNum());
        req.setTwoStarNum(gameAssessEntity.getTwoStarNum());
        req.setThereStarNum(gameAssessEntity.getThereStarNum());
        req.setFourStarNum(gameAssessEntity.getFourStarNum());
        req.setFiveStarNum(gameAssessEntity.getFiveStarNum());

        req.setLabels(labelLibraryService.getByGidLabel(params.getGid()));

        req.setDesc(gameDataEntity.getContent());

        //TODO:获取留言

        return req;
    }


    /**
     * 根据gid获取留言
     * @param gid
     */
    public void getMessageByGid(String gid){


    }

}
