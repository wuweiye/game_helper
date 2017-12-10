package com.dkm.game.article.req;

import com.dkm.base.BaseReq;
import lombok.Data;

@Data
public class GameArticleReq extends BaseReq {

    private String id;

    private String title;


    private String content;

    private int isShow;


}
