package com.dkm.model.user;

import com.dkm.model.base.BaseEntity;
import com.dkm.myenum.GameEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "game_user")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class UserEntity  extends BaseEntity{


    private String userName;

    private String password;

    private Date lastLoginTime;

    private String lastLoginId;

    private String regIp;




}
