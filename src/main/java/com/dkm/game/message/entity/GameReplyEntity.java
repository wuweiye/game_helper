package com.dkm.game.message.entity;


import com.dkm.base.BaseEntity;
import com.dkm.game.data.myenum.GameEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "game_reply")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameReplyEntity extends BaseEntity{


   /* @Id
    @GenericGenerator(name = "uuid", strategy = "uuid.hex")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "uuid")
    private String id;*/

    private Long gid = 0l;

    private Long wid = 0l;

    private Long uid = 0l;

    @Length(max = 500)
    private String content = "0";


    /**
     * 是否显示
     */
    private int isShow = 0;


   /* *//**
     * 状态
     *//*
    private  String status = GameEnum.Status.VALID.getValue();

    private Date updateTime = new Date();
    private Date createTime = new Date();
    private String createBy = "0";
    private String updateBy = "0";*/
}
