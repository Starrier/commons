package org.starrier.common.picture;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author starrier
 * @date 2021/1/7
 */
public class WaterMarkUtils {


    /**
     * 图片水印
     *
     * @param pressImage       水印图片文件
     * @param pressTargetImage 目标图片
     * @param x                水印位置
     * @param y                水印位置
     */
    public static void pressImage(String pressImage, String pressTargetImage, Integer x, Integer y) {

        try {
            // 目标文件
            File file = new File(pressTargetImage);
            Image image = ImageIO.read(file);

            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics graphics = bufferedImage.createGraphics();
            graphics.drawImage(image, 0, 0, width, height, null);

            // 水印文件
            File waterMarkImage = new File(pressImage);
            Image waterImage = ImageIO.read(waterMarkImage);
            int weightWaterMarkImage = waterImage.getHeight(null);
            int heightWaterMarkImage = waterImage.getHeight(null);

            graphics.drawImage(waterImage, x, y, weightWaterMarkImage, heightWaterMarkImage, null);

            // 水印结束
            graphics.dispose();

            String formatName = pressTargetImage.substring(pressTargetImage.lastIndexOf(".") + 1);
            FileOutputStream fileOutputStream = new FileOutputStream(pressTargetImage);
            ImageIO.write(bufferedImage, /*"GIF"*/ formatName /* format desired */, new File(pressTargetImage) /* target */);
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void pressText(String pressText, String targetImage, String fontName, Integer fontStyle,
                                 Color color, Integer fontSize, Integer x, Integer y) {
        try {
            File file = new File(targetImage);
            Image image = ImageIO.read(file);
            int width = image.getWidth(null);
            int height = image.getHeight(null);

            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics graphics = bufferedImage.getGraphics();

            graphics.drawImage(image, 0, 0, width, height, null);
            graphics.setColor(color);
            graphics.setFont(new Font(fontName, fontStyle, fontSize));
            graphics.drawString(pressText, x, y);

            graphics.dispose();

            String formatName = targetImage.substring(targetImage.lastIndexOf(".") + 1);
            FileOutputStream fileOutputStream = new FileOutputStream(targetImage);
            ImageIO.write(bufferedImage, /*"GIF"*/ formatName /* format desired */, new File(pressText) /* target */);
            fileOutputStream.close();
        } catch (Exception e) {

        }
    }
}
