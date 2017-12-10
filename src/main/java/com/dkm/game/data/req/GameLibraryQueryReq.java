package com.dkm.game.data.req;


import com.dkm.base.BaseReq;
import lombok.EqualsAndHashCode;

import java.sql.Date;
import java.sql.Timestamp;

@lombok.Data
@EqualsAndHashCode(callSuper = false)
public class GameLibraryQueryReq extends BaseReq{

    private  String gId;

    private String name;

    private String content;



}
