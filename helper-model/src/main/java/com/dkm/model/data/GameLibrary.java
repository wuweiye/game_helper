package com.dkm.model.data;

import com.dkm.model.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "GAME_LIBRARY")
@Data
@NoArgsConstructor
public class GameLibrary extends BaseEntity {

    /*@Id
    @GenericGenerator(name = "uuid", strategy = "uuid.hex")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "uuid")
    private String gId;*/



    private String name  = "test";


    private String logoUrl;
}
