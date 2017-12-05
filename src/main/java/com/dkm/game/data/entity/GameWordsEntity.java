package com.dkm.game.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "game_article")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameWordsEntity {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid.hex")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid")
    private String id;


    @Length(max = 500)
    private String content;

    private int isShow;

    private String status;


    private Date updateTime = new Date();
    private Date createTime = new Date();
    private String createBy = "0";
    private String updateBy = "0";

}
