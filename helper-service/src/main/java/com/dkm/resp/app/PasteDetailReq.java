package com.dkm.resp.app;

import com.dkm.model.forum.ReplyMain;
import com.dkm.resp.base.BaseReq;
import lombok.Data;

import java.util.List;

@Data
public class PasteDetailReq extends BaseReq{

    private String content;

    private String author;

    private Integer replyNum;

    private List<ReplyMain> replyMains;
}
