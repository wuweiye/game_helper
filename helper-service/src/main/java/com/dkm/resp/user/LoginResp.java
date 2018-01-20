package com.dkm.resp.user;

import com.dkm.basic.component.ext.web.BaseResp;
import lombok.Data;

@Data
public class LoginResp extends BaseResp{

        private String userId;

        private String time;

        private String key;

}
