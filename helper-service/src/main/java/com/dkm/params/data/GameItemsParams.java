package com.dkm.params.data;

import com.dkm.params.base.BaseParams;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GameItemsParams extends BaseParams {



    @NotNull(message = "名称不能为空")
    private String name;

    @NotNull(message = "描述不能为空")
    private String desc;

    @NotNull(message = "gid 不能为空")
    private Long gid;

    @NotNull(message = "路径不能为空")
    private String urlPath;
}
