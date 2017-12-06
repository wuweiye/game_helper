package com.dkm.game.data.req;

import com.dkm.base.BaseReq;
import lombok.Data;

import java.util.Date;

@Data
public class GameArticleReq extends BaseReq {

    private String id;

    private String title;

    private String content;

    private int isShow;


}
