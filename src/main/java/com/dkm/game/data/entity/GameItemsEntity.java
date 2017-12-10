package com.dkm.game.data.entity;

import com.dkm.base.BaseEntity;
import com.dkm.game.data.myenum.GameEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "game_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameItemsEntity extends BaseEntity {




    private String gid ;

    private String name ;

    private String description;

    private String urlPath ;

}
