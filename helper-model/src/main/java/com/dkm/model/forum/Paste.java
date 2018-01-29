package com.dkm.model.forum;


import com.dkm.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "f_paste")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Paste  extends BaseEntity{

    private Long gid;

    private Long fid;

    private Long uid;

    private String title;

    private String content;

    private int isShow = 1;

    private int goodNum =0;

    private int replyNum = 0;


}
