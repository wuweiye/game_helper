package com.dkm.game.data.req;


import com.dkm.base.BaseReq;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GameLabelReq extends BaseReq{

    private String id;

    @NotNull(message = "找不到对应游戏")
    private String gid;
    @NotNull(message = "找不到对应标签")
    private String lid;
    

}
