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
@Table(name = "label_library")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabelLibraryEntity  extends BaseEntity{



    private String name = "test";


}
