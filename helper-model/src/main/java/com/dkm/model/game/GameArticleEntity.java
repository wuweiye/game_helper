package com.dkm.model.game;

import com.dkm.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "game_article")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class GameArticleEntity extends BaseEntity {


    private Long gid;

    private Long uid;

    private String title;

    private String content;

    private int isShow = 1;

    private int goodNum =0;

    private int replyNum = 0;


}
