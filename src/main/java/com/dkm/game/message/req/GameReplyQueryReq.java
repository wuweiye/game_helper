package com.dkm.game.message.req;

import com.dkm.base.BaseReq;
import com.dkm.game.data.myenum.GameEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameReplyQueryReq extends BaseReq{


    private String content;

    //TODO : 后期改为用户名
    private String uid;


    //TODO ：回复贴ID
    private String wid;

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
