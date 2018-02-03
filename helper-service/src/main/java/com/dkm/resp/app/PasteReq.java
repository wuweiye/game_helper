package com.dkm.resp.app;

import com.dkm.resp.base.BaseReq;
import lombok.Data;

@Data
public class PasteReq extends BaseReq{

    private String title;


    private String content;

    private String author;

    private Integer replyNum;

    private Integer followNum;
}
