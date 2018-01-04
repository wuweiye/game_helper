package com.dkm.resp.data;


import com.dkm.resp.base.BaseReq;
import lombok.Data;

@Data
public class GameLabelReq extends BaseReq {

    private Long gid;

    private Long lid;

    private String labelName;

    private String gameName;


}
