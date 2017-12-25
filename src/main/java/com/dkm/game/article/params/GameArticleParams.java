package com.dkm.game.article.params;

import com.dkm.base.BaseParams;
import com.dkm.base.BaseReq;
import lombok.Data;

import java.util.Date;

@Data
public class GameArticleParams extends BaseParams {


    private Long id;

    private Long uid;

    private Long gid;

    private String title;

    private String content;

    private int isShow;


}
