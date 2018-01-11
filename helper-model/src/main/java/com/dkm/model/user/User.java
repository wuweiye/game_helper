package com.dkm.model.user;


import com.dkm.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class User  extends BaseEntity implements Serializable{

    private String username; //用户名
    private String password; //密码
    private String salt; //加密密码的盐

}
