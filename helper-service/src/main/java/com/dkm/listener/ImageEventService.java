package com.dkm.listener;

import com.dkm.listener.event.ImageEvent;
import com.dkm.service.image.ImageService;
import com.dkm.utils.ImageUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
public class ImageEventService {



    ApplicationEventPublisher eventPublisher;

    public void saveImage(MultipartFile file , String path, String copyPath,String source){

        System.out.println("saveImage start==========================");
        ImageEvent event = new ImageEvent();
        event.setImageFile(file);
        event.setImgSrc(path);
        event.setSource(source);
        event.setCopySrc(copyPath);

        eventPublisher.publishEvent(event);


    }

    @Async
    @EventListener
    public void OnListener(ImageEvent event){

        System.out.println("OnListener start==========================");

        try {
            //保存图片
            Files.copy(event.getImageFile().getInputStream(), Paths.get(event.getCopySrc()));
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(event.getSource() == ImageUtils.ITEM){
            //压缩图片
            ImageUtils.reduceImg(event.getCopySrc(), event.getImgSrc(), 100,100);
        }else {
            ImageUtils.reduceImg(event.getCopySrc(), event.getImgSrc(), 1f);
        }




        System.out.println("OnListener end==========================");

    }
}
