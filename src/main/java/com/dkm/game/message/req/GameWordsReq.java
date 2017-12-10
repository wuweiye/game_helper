package com.dkm.game.message.req;

import com.dkm.base.BaseReq;
import lombok.Data;

@Data
public class GameWordsReq extends BaseReq {

    private String gid;

    private String content;

    private String uid;

}
