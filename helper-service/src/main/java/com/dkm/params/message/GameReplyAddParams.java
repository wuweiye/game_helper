package com.dkm.params.message;


import com.dkm.params.base.BaseParams;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GameReplyAddParams extends BaseParams{


    @NotNull
    private Long gid;

    @NotNull
    private Long wid;

    @NotNull
    private Long uid;

    @NotNull
    private String content;
}
