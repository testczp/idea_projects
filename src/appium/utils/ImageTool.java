package appium.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by chenzepeng on 2020/3/20.
 */
public class ImageTool {

    public static boolean compareImg(File a, File b){

        BufferedImage image1 = readImg(a);
        BufferedImage image2 = readImg(b);
        double same = differentImages(image1, image2);
        if(same > 99){
            return true;
        }else{
            return false;
        }

    }

    public static BufferedImage readImg(File a){
        BufferedImage imageA = null;
        try {
            imageA = ImageIO.read(a);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageA;
    }

    public static double differentImages(BufferedImage a1, BufferedImage a2){
        double same = 0.0;
        int height1 = a1.getHeight();
        int width1 = a1.getWidth();
        int height2 = a2.getHeight();
        int width2 = a2.getWidth();
        double diff = 0.0;
        if(height1 != height2 || width1 != width2){
            return same;
        }
        for(int i = 0; i < width1; i++){
            for(int j = 0; j < height1; j++){
                if(a1.getRGB(i,j) != a2.getRGB(i,j)){
                    diff++;
                }
            }
        }
        if(diff != 0){
            same = 1 - diff / width1 * height1;
        }
        System.out.println("匹配率为:" + same);
        return same;
    }
}
