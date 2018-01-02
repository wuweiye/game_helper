package com.dkm.game.message.params;


import com.dkm.base.BaseParams;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GameWordsAddParams extends BaseParams {


    @NotNull
    private Long gid;

    @NotNull
    private Long uid;

    @NotNull
    private String content;
}
