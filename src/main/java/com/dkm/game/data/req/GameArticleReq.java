package com.dkm.game.data.req;

import lombok.Data;

import java.util.Date;

@Data
public class GameArticleReq {

    private String id;

    private String title;

    private String content;

    private int isShow;

    private String status;

    private Date updateTime = new Date();
    private Date createTime = new Date();
    private String createBy = "0";
    private String updateBy = "0";
}
