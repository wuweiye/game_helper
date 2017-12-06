package com.dkm.game.message.entity;


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
public class GameReplyEntity {


    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid.hex")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String gid;

    private String wid;

    @Length(max = 500)
    private String content;


    /**
     * 是否显示
     */
    private int isShow;


    /**
     * 状态
     */
    private  String status = GameEnum.Status.VALID.getValue();

    private Date updateTime = new Date();
    private Date createTime = new Date();
    private String createBy = "0";
    private String updateBy = "0";
}
