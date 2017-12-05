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
@Table(name = "game_assess")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameAssessEntity {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid.hex")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid")
    private String id;

    private int oneStarNum = 0;
    private int twoStarNum = 0;
    private int thereStarNum = 0;
    private int fourStarNum = 0;
    private int fiveStarNum = 0;

    private Date updateTime = new Date();
    private Date createTime = new Date();
    private String createBy = "0";
    private String updateBy = "0";

}
