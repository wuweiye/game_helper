package com.dkm.game.data.req;


import lombok.EqualsAndHashCode;

import java.sql.Date;
import java.sql.Timestamp;

@lombok.Data
@EqualsAndHashCode(callSuper = false)
public class GameLibraryQueryReq {

    private  String gId,name,updateTime;

    private boolean status;

   /* private String oid, channelOid, title, imageUrl, linkUrl, approveStatus, releaseStatus,
            operator, approveOpe, releaseOpe, remark, toPage;*/

    private Timestamp approveTime, releaseTime;
    /**
     * 区分链接和调整 0：链接  1：跳转
     */
    private int isLink;
}
