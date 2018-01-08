package com.dkm.resp.data;


import com.dkm.resp.base.BaseReq;
import lombok.EqualsAndHashCode;

@lombok.Data
@EqualsAndHashCode(callSuper = false)
public class GameLibraryQueryReq extends BaseReq{

    private  String gId;

    private String name;

    private String content;

    private Integer followCount;

    private String assess;



}
