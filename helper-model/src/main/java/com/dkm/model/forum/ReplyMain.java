package com.dkm.model.forum;


import com.dkm.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "f_reply_main")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ReplyMain extends BaseEntity{

    Long pid ;

    Long uid;

    String content;


}
