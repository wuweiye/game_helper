package com.dkm.utils;

import com.dkm.base.Constants;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;

public class ImageUtils {



    public final static String ITEM = "item";
    public final static String GAME_ICON = "game_icon";
    public final static Integer I100 = 100;


  /*  public static void main(String [] args){

        File file = new File("D:\\12.jpg");
        BufferedInputStream in = null;

        changeSize(PATH,100,100);

    }*/

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

    public static BufferedImage changeSize(String path, Integer width, Integer height){


        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(path));
            Image bi = ImageIO.read(in);
            BufferedImage tag = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(bi,0,0,width,height,null);

             /*return tag;*/
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("D:\\" +"copy2.jpg"));
            ImageIO.write(tag,"jpg",out);
            in.close();
            out.close();
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            try {
                if(in != null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  null;

    }


    private static void saveImage(String path, BufferedImage image){
       /* BufferedOutputStream out = null;*/
        String suffix = path.substring(path.lastIndexOf(".")).toLowerCase();

        try {
             /*out = new BufferedOutputStream(new FileOutputStream(path));
             ImageIO.write(image,suffix,out);*/
            ImageIO.write(image,suffix, new File(path));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void reduceImg(String imgSrc,String redSrc, int width, int height) {

        reduceImg(imgSrc,redSrc,width,height,null);
    }

    public static void reduceImg(String imgSrc,String redSrc, Float f) {

        reduceImg(imgSrc,redSrc,0,0,f);
    }


    /**
     * 压缩图片
     * @param imgsrc 源路径
     * @param imgdist 目标路径
     * @param widthdist 压缩宽度
     * @param heightdist 压缩高度
     * @param rate   压缩比例
     */
    private static void reduceImg(String imgsrc, String imgdist, int widthdist,
                                 int heightdist, Float rate) {
        try {
            File srcfile = new File(imgsrc);

            System.out.println(imgsrc + "---" + imgdist);
            // 检查文件是否存在
            if (!srcfile.exists()) {
                System.out.println("文件不存在");
                return;
            }
            // 如果rate不为空说明是按比例压缩
            if (rate != null && rate > 0) {
                // 获取文件高度和宽度
                int[] results = getImgWidth(srcfile);
                if (results == null || results[0] == 0 || results[1] == 0) {
                    return;
                } else {
                    widthdist = (int) (results[0] * rate);
                    heightdist = (int) (results[1] * rate);
                }
            }

            System.out.println(widthdist  + "---" + heightdist);

            // 开始读取文件并进行压缩
            Image src = javax.imageio.ImageIO.read(srcfile);
            BufferedImage tag = new BufferedImage( widthdist,
                    heightdist, BufferedImage.TYPE_INT_RGB);

            tag.getGraphics().drawImage(
                    src.getScaledInstance(widthdist, heightdist,
                            Image.SCALE_SMOOTH), 0, 0, null);

            FileOutputStream out = new FileOutputStream(imgdist);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(tag);
            out.close();

            System.out.println("---------OK----------");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 获取图片宽度
     *
     * @param file
     *            图片文件
     * @return 宽度
     */
    public static int[] getImgWidth(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int result[] = { 0, 0 };
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            result[0] = src.getWidth(null); // 得到源图宽
            result[1] = src.getHeight(null); // 得到源图高

            System.out.println(result[0] + "--" +result[1]);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
