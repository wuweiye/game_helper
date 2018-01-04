package com.dkm.params.message;


import com.dkm.params.base.BaseParams;
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
