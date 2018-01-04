package com.dkm.model.image;

import com.dkm.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "image_record")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ImageRecordEntity extends BaseEntity {

    String type;

    String imageMD5;

    String imageName;

    String urlPath;
}
