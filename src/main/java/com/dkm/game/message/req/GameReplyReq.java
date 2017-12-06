package com.dkm.game.message.req;

import com.dkm.base.BaseReq;
import lombok.Data;

@Data
public class GameReplyReq extends BaseReq{

    private String gid;

    private String wid;

    private String uid;

    private String content;



}
