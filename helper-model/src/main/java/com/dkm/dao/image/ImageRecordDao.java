package com.dkm.dao.image;

import com.dkm.model.image.ImageRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRecordDao extends JpaRepository<ImageRecordEntity, Long> {


    public ImageRecordEntity findByImageMD5AndType(String imageMd5, String type);
}
