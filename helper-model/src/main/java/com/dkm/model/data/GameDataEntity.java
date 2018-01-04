package com.dkm.model.data;

import com.dkm.model.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "game_data")
@Data
@NoArgsConstructor
public class GameDataEntity extends BaseEntity {


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
