package com.dkm.model.data;

import com.dkm.model.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "game_assess")
@Data
@NoArgsConstructor
public class GameAssessEntity extends BaseEntity {


    private Long gid;

    private int oneStarNum = 0;
    private int twoStarNum = 0;
    private int thereStarNum = 0;
    private int fourStarNum = 0;
    private int fiveStarNum = 0;


    public int getTotal(){

        return  this.oneStarNum + this.twoStarNum + this.thereStarNum + this.fourStarNum + this.fiveStarNum;
    }

    public int getScale(int num){

        return num/getTotal() *100;
    }

}
