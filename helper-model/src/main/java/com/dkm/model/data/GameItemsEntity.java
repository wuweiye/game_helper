package com.dkm.model.data;

import com.dkm.model.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "game_items")
@Data
@NoArgsConstructor
public class GameItemsEntity extends BaseEntity {




    private Long gid ;

    private String name ;

    private String description;

    private String urlPath ;

}
