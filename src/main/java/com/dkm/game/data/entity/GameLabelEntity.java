package com.dkm.game.data.entity;

import com.dkm.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "game_label")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameLabelEntity extends BaseEntity{



    private String lid;

    private String gid;


}
