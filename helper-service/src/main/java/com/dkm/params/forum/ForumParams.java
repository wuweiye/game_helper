package com.dkm.params.forum;

import com.dkm.params.base.BaseParams;

import javax.validation.constraints.NotNull;

public class ForumParams extends BaseParams {

    @NotNull(message = "标题不能为空！")
    String name;
}
