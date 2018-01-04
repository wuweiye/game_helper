package com.dkm.resp.base;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
