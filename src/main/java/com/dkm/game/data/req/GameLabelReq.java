package com.dkm.game.data.req;


import com.dkm.base.BaseReq;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GameLabelReq extends BaseReq{

    private Long gid;

    private Long lid;

    private String labelName;

    private String gameName;


}
