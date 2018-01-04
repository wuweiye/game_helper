package com.dkm.params.data;

import com.dkm.params.base.BaseParams;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@lombok.Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class GameLibraryParams extends BaseParams {


    private Long gid = 0L;

    @NotNull(message = "标题不能为空！")
    @Length(max = 60, message = "标题长度不能超过60（包含）！")
    private String name;

    private String desc;

    private String developStore;

    private String urlPath;

}
