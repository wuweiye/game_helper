package com.dkm.game.data.entity;

import com.dkm.base.BaseEntity;
import com.dkm.game.data.myenum.GameEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "game_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameDataEntity extends BaseEntity{


    private Long gid;

    /**
     * 内容介绍
     */
    @Length(max = 300)
    private String content;

    private int followCount = 0;

    private String urlPath = "none";

    private String developStore = "未知";

}
