package com.dkm.resp.message;

import com.dkm.resp.base.BaseReq;
import lombok.Data;

@Data
public class GameWordsReq extends BaseReq {

    private Long gid;

    private String content;

    private Long uid;

}
