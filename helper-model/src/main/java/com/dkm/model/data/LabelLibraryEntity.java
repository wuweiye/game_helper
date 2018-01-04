package com.dkm.model.data;

import com.dkm.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "label_library")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class LabelLibraryEntity  extends BaseEntity {



    private String name = "test";


}
