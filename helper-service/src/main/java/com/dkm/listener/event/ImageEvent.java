package com.dkm.listener.event;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageEvent {

    MultipartFile imageFile;

    String imgSrc;

    String copySrc;

    String source;

}
