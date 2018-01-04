package com.dkm.resp.message;

import com.dkm.resp.base.BaseReq;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GameWordsQueryReq extends BaseReq {


    @NotNull
    private Long gid;

    private String content;

    //TODO : 后期改为用户名
    private Long uid;

    /**
     * 是否显示
     */
    private int isShow = 1;

    /**
     * 被赞总数
     */
    private int goodNum = 0;

    /**
     * 回复总数
     */
    private int replyNum = 0;






}
