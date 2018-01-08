package com.dkm.service.image;

import com.dkm.base.Constants;
import com.dkm.dao.image.ImageRecordDao;
import com.dkm.listener.ImageEventService;
import com.dkm.model.image.ImageRecordEntity;
import com.dkm.utils.FileUtils;
import com.dkm.utils.ImageUtils;
import com.dkm.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ImageServiceImpl implements ImageService {

    Logger log = Logger.getLogger(ImageService.class);

    public static final String ROOT = "../upload";
    public static final String ROOT_COPY = "../upload/copy";

    @Autowired
    public ImageRecordDao imageRecordDao;

    @Autowired
    public ImageEventService imageEventService;

    public String test() {
        return null;
    }


    public String upload(MultipartFile file, String source) {

        File folder = new File(ROOT);
        if (!folder.exists()) {
            folder.mkdir();
        }
        File folder2 = new File(ROOT_COPY);
        if (!folder2.exists()) {
            folder2.mkdir();
        }

        String replayName;
        if (!file.isEmpty()) {


            if(StringUtils.isEmpty(source)){
                Constants.sys("source is empty");
                source = ImageUtils.ITEM;
            }
            String imageMd5 = null;
            try {
                imageMd5 = FileUtils.getInputStreamDigest(file.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            String name = file.getOriginalFilename();
            replayName = ImageUtils.getReplayName(name);

            ImageRecordEntity imageRecord= imageRecordDao.findByImageMD5AndType(imageMd5,source);
            if(imageRecord != null){
                log.info(" 图片已经存在,不在保存此图片");

                return imageRecord.getUrlPath();
            }


            log.info(" 图片不存在,保存此图片");
            if(imageMd5 != null){

                imageRecord = new ImageRecordEntity();
                imageRecord.setImageMD5(imageMd5);
                imageRecord.setImageName(name);
                imageRecord.setType(source);
                imageRecord.setUrlPath(Paths.get(ROOT, replayName).toString());
                imageRecordDao.saveAndFlush(imageRecord);

                imageEventService.saveImage(file,Paths.get(ROOT, replayName).toString(),Paths.get(ROOT_COPY, name).toString(),source);


                /*Files.copy(file.getInputStream(), Paths.get(ROOT, replayName));*/
                /*;

                ImageUtils.copy(file.getInputStream(), Paths.get(ROOT, replayName).toString(),source);*/
                return Paths.get(ROOT, replayName).toString();
            }

            //Files.copy(file.getInputStream(), Paths.get(ROOT, file.getOriginalFilename()));

        }


        return "none";
    }
}
