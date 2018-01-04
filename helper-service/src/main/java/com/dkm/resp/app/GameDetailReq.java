package com.dkm.resp.app;


import com.dkm.resp.base.BaseReq;
import lombok.Data;

import java.util.List;

@Data
public class GameDetailReq extends BaseReq {

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
