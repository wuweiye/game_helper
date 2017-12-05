package com.dkm.game.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "GAME_Label")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameLabelEntity{

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid.hex")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid")
    private String id;

    /**
     * LibelLibrary 主键
     */
    private String lid;

    private String gid;

    private boolean status;

    private Date updateTime = new Date();
    private Date createTime = new Date();
    private String createBy = "0";
    private String updateBy = "0";

}
