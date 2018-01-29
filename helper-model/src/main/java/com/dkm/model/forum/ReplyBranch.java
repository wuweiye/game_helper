package com.dkm.model.forum;


import com.dkm.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "f_reply_branch")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ReplyBranch extends BaseEntity{


    Long pid ;

    Long uid;

    Long parent;

    String content;
}
