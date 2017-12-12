package com.dkm.game.data.params;


import com.dkm.base.BaseParams;
import com.dkm.base.BaseReq;
import com.dkm.game.data.myenum.GameEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GameLabelParams extends BaseParams{

    @NotNull
    private String gid;

    @NotNull
    private String lid;




}
