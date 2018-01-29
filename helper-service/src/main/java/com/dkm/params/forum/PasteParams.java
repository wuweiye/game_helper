package com.dkm.params.forum;

import com.dkm.params.base.BaseParams;
import lombok.Data;

@Data
public class PasteParams extends BaseParams {

    Long gid;

    Long uid;

    Long fid;

    private String title;

    private String content;
}
