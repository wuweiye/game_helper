package com.dkm.game.user.entity;

import com.dkm.game.data.myenum.GameEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "game_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity{

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid.hex")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid")
    private String id;


    private String status = GameEnum.Status.VALID.getValue();


    private String userName;

    private String password;

    private Date lastLoginTime;

    private String lastLoginId;

    private String regIp;


    private Date updateTime = new Date();
    private Date createTime = new Date();
    private String createBy = "0";
    private String updateBy = "0";


}
