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

    //TODO:评价list
    public List list;


}
