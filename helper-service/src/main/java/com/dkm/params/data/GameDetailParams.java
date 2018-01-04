package com.dkm.params.data;

import com.dkm.params.base.BaseParams;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class GameDetailParams extends BaseParams {

    @NotNull(message = "gid 不能为空")
    private Long gid;

}
