package com.dkm.game.article.entity;

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
public class GameArticleEntity {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid.hex")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid")
    private String id;

    private String title;

    private String content;

    private int isShow = 1;

    private String status = GameEnum.Status.VALID.getValue();

    private Date updateTime = new Date();
    private Date createTime = new Date();
    private String createBy = "0";
    private String updateBy = "0";

}
