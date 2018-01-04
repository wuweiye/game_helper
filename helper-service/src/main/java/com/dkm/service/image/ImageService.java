package com.dkm.service.image;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    public String test();

    /**
     * 上传图片
     * @param file 图片
     * @param source 上传来源
     * @return
     */
    public String upload(MultipartFile file, String source);
}
