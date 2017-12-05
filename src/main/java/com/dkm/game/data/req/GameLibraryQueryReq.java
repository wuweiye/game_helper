package com.dkm.game.data.req;


import lombok.EqualsAndHashCode;

import java.sql.Date;
import java.sql.Timestamp;

@lombok.Data
@EqualsAndHashCode(callSuper = false)
public class GameLibraryQueryReq {

    private  String gId,name,updateTime;

    private boolean status;
    
    private Timestamp approveTime, releaseTime;

}
