package com.dkm.game.data.req;

import com.dkm.base.BaseReq;
import lombok.Data;

@Data
public class GameItemsReq extends BaseReq{


    private String name;

    private String desc;

    private Long gid;

    private String gameName;

    private String urlPath;

}
