package com.dkm.base;


import com.dkm.game.data.myenum.GameEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseReq {


    private Long id;

    private  String status = "none";

    private String createTime = "0";

    private String createBy ="0";

    private String updateTime = "0";

    private String updateBy = "0";
}
