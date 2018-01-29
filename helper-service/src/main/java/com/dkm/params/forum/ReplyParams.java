package com.dkm.params.forum;

import com.dkm.params.base.BaseParams;
import lombok.Data;

@Data
public class ReplyParams extends BaseParams {

    Long uid;

    Long pid;

    Long parent;

    String content;


}
