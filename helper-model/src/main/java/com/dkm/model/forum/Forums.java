package com.dkm.model.forum;

import com.dkm.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "f_forums")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Forums extends BaseEntity {


    /**
     * 关联键
     */
    private Long gid;

    /**
     * 论坛名
     */
    private String name ;

    /**
     * 关注人数
     */
    private int followNum =0;

    /**
     * 发帖数
     */
    private int replyNum = 0;



}
