package com.dkm.game.data.entity;

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
@Table(name = "game_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameDataEntity {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid.hex")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid")
    private String id;


    /**
     * 内容介绍
     */
    @Length(max = 300)
    private String content;

    private String status = GameEnum.Status.VALID.getValue();


    private Date updateTime = new Date();
    private Date createTime = new Date();
    private String createBy = "0";
    private String updateBy = "0";

}
