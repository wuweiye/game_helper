package com.dkm.model.message;

import com.dkm.model.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "game_article")
@Data
@NoArgsConstructor
public class GameWordsEntity extends BaseEntity {
/*
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid.hex")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid")
    private String id;*/

    private Long gid;

    private Long uid;

    @Length(max = 500)
    private String content;

    /**
     * 是否显示
     */
    private int isShow = 1;

    /**
     * 被赞总数
     */
    private int goodNum = 0;

    /**
     * 回复总数
     */
    private int replyNum = 0;

    /**
     * 状态
     *//*
    private  String status = GameEnum.Status.VALID.getValue();

    private Date updateTime = new Date();
    private Date createTime = new Date();
    private String createBy = "0";
    private String updateBy = "0";*/

}
