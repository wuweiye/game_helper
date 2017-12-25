package com.dkm.game.app.service;

import com.dkm.game.app.req.GameDetailReq;
import com.dkm.game.data.entity.*;
import com.dkm.game.data.params.GameDetailParams;
import com.dkm.game.data.service.DataManageService;
import com.dkm.game.data.service.LabelLibraryService;
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

        //TODO: 后期添加 图片路径，生产商，关注人数
        GameLibrary gameLibrary = dataManageService.getByGidDetail(params.getGid());
        GameDataEntity gameDataEntity = dataManageService.getGameDataEntityByGid(params.getGid());
        GameAssessEntity gameAssessEntity = dataManageService.getGameAssessByGid(params.getGid());

        req.setGameName(gameLibrary.getName());
        req.setFollowCount(0);
        req.setDevelopStore("未知");
        req.setUrlPath("http://bpic.588ku.com/element_origin_min_pic/16/10/30/528aa13209e86d5d9839890967a6b9c1.jpg");

        req.setLabels(labelLibraryService.getByGidLabel(params.getGid()));

        req.setDesc(gameDataEntity.getContent());


        return req;
    }


    /**
     * 根据gid获取留言
     * @param gid
     */
    public void getMessageByGid(String gid){
        //TODO:获取留言

    }

    /*private int accumulate(int a) {
        if (a <= 0) {
            return 0;
        }
        int sum = 0;
        for(int i = 0; i <= a; i++) {
            sum += a;
        }
        return sum;
    }*/

}
