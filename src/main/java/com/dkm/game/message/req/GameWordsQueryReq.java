package com.dkm.game.message.req;

import com.dkm.base.BaseReq;
import com.dkm.game.data.myenum.GameEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class GameWordsQueryReq extends BaseReq {


    @NotNull
    private String gid;

    private String content;

    //TODO : 后期改为用户名
    private String uid;

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
