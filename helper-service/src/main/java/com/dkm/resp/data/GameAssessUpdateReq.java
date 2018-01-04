package com.dkm.resp.data;


import com.dkm.resp.base.BaseReq;
import lombok.Data;

@Data
public class GameAssessUpdateReq extends BaseReq{

    private Long id;

    private String Star;

}
