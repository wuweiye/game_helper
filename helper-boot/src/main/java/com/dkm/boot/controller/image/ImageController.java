package com.dkm.boot.controller.image;


import com.dkm.service.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.Iterator;

@RequestMapping(value = "/game/image/")
@RestController
public class ImageController {


    @Autowired
    public ImageService imageService;

    private final ResourceLoader resourceLoader;

    @Autowired
    public ImageController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public static final String ROOT = "../upload";


    @RequestMapping(method = RequestMethod.GET, value = "/show/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename) {

        try {

            Resource resource = resourceLoader.getResource("file:" + Paths.get(ROOT, filename).toString());
            return ResponseEntity.ok(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("imageUpload")
    public void imageUpload(HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes) {


        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        Iterator<String> iter = multiRequest.getFileNames();
        while (iter.hasNext()) {


        }

    }


    @RequestMapping(value = "yup2")
    public String handleFileUpload(@RequestParam("urlPath") MultipartFile file,
                                   @RequestParam("source") String source,
                                    HttpServletRequest request) {

        return imageService.upload(file,source);
    }


    @RequestMapping(value="yup")
    public String uploadImg(@RequestParam("urlPath") MultipartFile file,
                     HttpServletRequest request) {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);
        String filePath = Paths.get(ROOT, fileName).toString();
        try {
            uploadFile(file.getBytes(), filePath);
        } catch (Exception e) {
            // TODO: handle exception
        }
        //返回json
        return filePath;
    }


    public static void uploadFile(byte[] file, String filePath) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath);
        out.write(file);
        out.flush();
        out.close();
    }
}
