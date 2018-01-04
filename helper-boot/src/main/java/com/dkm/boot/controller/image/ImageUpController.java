package com.dkm.boot.controller.image;

import com.dkm.base.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

@Controller
@RequestMapping("/game/image/test/")
public class ImageUpController {

    public static final String ROOT = "upload";


    @RequestMapping("imageUpload")
    public void imageUpload(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {


        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        Iterator<String> iter = multiRequest.getFileNames();
        while (iter.hasNext()) {

            MultipartFile file = multiRequest.getFile(iter.next());
            if (!file.isEmpty()) {
                try {
                    Files.copy(file.getInputStream(), Paths.get(ROOT, file.getOriginalFilename()));

                } catch (IOException |RuntimeException e) {
                    Constants.sys("1122" +e);
                }
            }

            try {

                String url = ROOT +"/" + file.getOriginalFilename();
                response.setContentType("text/html;charset=UTF-8");
                String callback = request.getParameter("CKEditorFuncNum");
                PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + url + "',''" + ")");
                out.println("</script>");
            } catch (IOException e) {
                Constants.sys("erroe" + e);
            }


        }

    }
}
