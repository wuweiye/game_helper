package com.dkm.params.article;

import com.dkm.params.base.BaseParams;
import lombok.Data;

@Data
public class GameArticleParams extends BaseParams {


    private Long id;

    private Long uid;

    private Long gid;

    private String title;

    private String content;

    private int isShow;


}
