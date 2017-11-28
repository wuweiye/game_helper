package com.dkm.game.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "GAME_LIBRARY")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameLibrary {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid.hex")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "uuid")
    private String gId;

    private String name  = "test";

    private boolean status = true;
    private Date updateTime = new Date();
    private Date createTime = new Date();
}
