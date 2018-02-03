package com.dkm.resp.app;

import com.dkm.basic.component.ext.web.BaseResp;
import lombok.Data;

@Data
public class Reply extends BaseResp{

    Long pid ;

    Long uid;
    
    String name;

    Long parent;

    String content;
}
