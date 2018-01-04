package com.dkm.model.data;

import com.dkm.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "game_label")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class GameLabelEntity extends BaseEntity {



    private Long lid;

    private Long gid;


}
