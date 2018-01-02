package com.dkm.game.app.req;

import com.dkm.base.BaseReq;
import com.dkm.game.data.entity.GameLabelEntity;
import lombok.Data;

import java.util.List;

@Data
public class GameDetailReq extends BaseReq{

    public String gameName;

    public String developStore;

    public int followCount;

    private String urlPath ;

    public List<String> labels;

    public String desc;


    private int oneStarNum = 0;
    private int twoStarNum = 0;
    private int thereStarNum = 0;
    private int fourStarNum = 0;
    private int fiveStarNum = 0;

    private int total;

    private int scale;


    //TODO:评价list
    public List list;





}
