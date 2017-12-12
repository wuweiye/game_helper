package com.dkm.game.article.entity;

import com.dkm.base.BaseEntity;
import com.dkm.game.data.myenum.GameEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "game_article")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameArticleEntity extends BaseEntity{


    private String gid;

    private String uid;

    private String title;

    private String content;

    private int isShow = 1;

    private int goodNum =0;

    private int replyNum = 0;


}
