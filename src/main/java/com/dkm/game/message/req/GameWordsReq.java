package com.dkm.game.message.req;

import com.dkm.base.BaseReq;
import lombok.Data;

@Data
public class GameWordsReq extends BaseReq {

    private Long gid;

    private String content;

    private Long uid;

}
