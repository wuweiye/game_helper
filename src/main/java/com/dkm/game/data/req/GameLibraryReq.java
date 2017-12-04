package com.dkm.game.data.req;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@lombok.Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class GameLibraryReq {


    private String gid;

    @NotNull(message = "标题不能为空！")
    @Length(max = 60, message = "标题长度不能超过60（包含）！")
    private String name;

    private String status;

}
