package com.dkm.game.message.params;


import com.dkm.base.BaseParams;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GameWordsAddParams extends BaseParams {


    @NotNull
    private String gid;

    @NotNull
    private String uid;

    @NotNull
    private String content;
}
