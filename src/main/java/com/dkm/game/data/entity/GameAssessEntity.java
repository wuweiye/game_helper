package com.dkm.game.data.entity;

import com.dkm.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "game_assess")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameAssessEntity extends BaseEntity{


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
