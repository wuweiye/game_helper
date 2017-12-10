package com.dkm.game.data.req;

import com.dkm.base.BaseReq;
import lombok.Data;

@Data
public class GameAssessQueryReq extends BaseReq{

    private int oneStar,twoStar,thereStsr,fourStar,fiveStar;
}
