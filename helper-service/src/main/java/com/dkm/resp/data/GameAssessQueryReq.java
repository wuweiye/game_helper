package com.dkm.resp.data;

import com.dkm.resp.base.BaseReq;
import lombok.Data;

@Data
public class GameAssessQueryReq extends BaseReq {

    private int oneStar,twoStar,thereStsr,fourStar,fiveStar;
}
