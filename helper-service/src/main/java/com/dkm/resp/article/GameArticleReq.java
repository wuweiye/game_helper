package com.dkm.resp.article;

import com.dkm.resp.base.BaseReq;
import lombok.Data;

@Data
public class GameArticleReq extends BaseReq {

    private Long gid;

    private String gameName;

    private String title;


    private String content;

    private int isShow;




}
