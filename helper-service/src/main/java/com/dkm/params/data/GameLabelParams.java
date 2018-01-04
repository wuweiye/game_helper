package com.dkm.params.data;


import com.dkm.params.base.BaseParams;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GameLabelParams extends BaseParams {

    @NotNull
    private Long gid;

    @NotNull
    private Long lid;




}
