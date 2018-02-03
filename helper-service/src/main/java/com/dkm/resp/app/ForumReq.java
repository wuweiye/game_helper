package com.dkm.resp.app;

import com.dkm.resp.base.BaseReq;
import lombok.Data;

@Data
public class ForumReq extends BaseReq {

    private String name;


    private Integer pasteNum;

    private Integer followNum;
}
