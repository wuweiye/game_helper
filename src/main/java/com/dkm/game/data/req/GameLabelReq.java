package com.dkm.game.data.req;


import com.dkm.base.BaseReq;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GameLabelReq extends BaseReq{

    private String gid;

    private String lid;

    private String labelName;

    private String gameName;


}
