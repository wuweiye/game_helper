package com.dkm.game.image;


import com.dkm.base.Constants;
import com.dkm.game.utils.FileUtils;
import com.dkm.game.utils.ImageUploadUtil;
import com.dkm.game.utils.ImageUtils;
import com.dkm.game.utils.StringUtils;
import com.dkm.points.component.Constant;
import com.sun.imageio.plugins.common.ImageUtil;
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
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Iterator;

@RequestMapping(value = "/game/image/")
@RestController
public class ImageController {

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
                                   RedirectAttributes redirectAttributes, HttpServletRequest request) {


        File folder = new File(ROOT);
        if (!folder.exists()) {
            folder.mkdir();
        }

        String replayName;
        if (!file.isEmpty()) {
            try {

                Constants.sys("-------------start----------------");

                String imageMd5 = FileUtils.getInputStreamDigest(file.getInputStream());
                Constants.sys("-------------2----------------");
                String name = file.getOriginalFilename();
                Constants.sys("------------3----------------");
                replayName = ImageUtils.getReplayName(name);


                Constants.sys("imageMd5:" + imageMd5 +"----name:"+ name +"-----replayName:" + replayName);

                if(StringUtils.isEmpty(source)){
                    Constants.sys("source is empty");
                    source = ImageUtils.ITEM;
                }

                if(imageMd5 != null){
                    ImageUtils.copy(file.getInputStream(),Paths.get(ROOT, replayName).toString(),source);
                }

                return Paths.get(ROOT, replayName).toString();

                //Files.copy(file.getInputStream(), Paths.get(ROOT, file.getOriginalFilename()));
            } catch (IOException |RuntimeException e) {


                redirectAttributes.addFlashAttribute("message", "Failued to upload " + file.getOriginalFilename() + " => " + e.getMessage());
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Failed to upload " + file.getOriginalFilename() + " because it was empty");
        }

        return "none";
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
