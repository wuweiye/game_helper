package com.dkm.game.utils;

import com.dkm.base.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImageUtils {



    public final static String ITEM = "item";
    public final static String GAME_ICON = "game_icon";
    public final static Integer I100 = 100;

    /*private static List<String> fileTypes = new ArrayList<String>();
    static {
        fileTypes.add(".jpg");
        fileTypes.add(".jpeg");
        fileTypes.add(".bmp");
        fileTypes.add(".gif");
        fileTypes.add(".png");
    }
    */


    public static void copy(InputStream inputStream,String path,String type){

        BufferedImage image =  changeSize(inputStream, type);
        saveImage(path,image);
    }


    public static String getReplayName(String name){

            String suffix = name.substring(name.lastIndexOf(".")).toLowerCase();
            String fileName = Constants.imageNameFormat.format(new Date()) + suffix;

        return  fileName;
    }

    private static BufferedImage changeSize(InputStream inputStream,String type){
        BufferedInputStream in = null;
        int width = 0,height=0;

        if(type.equals(ITEM)){

            width = I100;
            height = I100;
        }else if(type.equals(GAME_ICON)){
            width = 400;
            height = 300;

        }

        try {
            Image image = ImageIO.read(inputStream);
            if(width != 0){
                BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
                bufferedImage.getGraphics().drawImage(image,0,0,width,height,null);


                return bufferedImage;

            }else {
                return ImageIO.read(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;


    }


    private static void saveImage(String path, BufferedImage image){
        BufferedOutputStream out = null;
        String suffix = path.substring(path.lastIndexOf(".")).toLowerCase();

        try {
             out = new BufferedOutputStream(new FileOutputStream(path));
             ImageIO.write(image,suffix,out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
