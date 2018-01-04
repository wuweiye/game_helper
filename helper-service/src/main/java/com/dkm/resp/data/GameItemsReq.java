package com.dkm.resp.data;

import com.dkm.resp.base.BaseReq;
import lombok.Data;

@Data
public class GameItemsReq extends BaseReq{


    private String name;

    private String desc;

    private Long gid;

    private String gameName;

    private String urlPath;

}
