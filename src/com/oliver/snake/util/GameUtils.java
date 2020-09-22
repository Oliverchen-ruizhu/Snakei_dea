package com.oliver.snake.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * @version 0.1
 * @Author: oliver chen
 * @Description: 图片处理
 * @Date:Create：in 2020/5/19 0:49
 * @Modified By：
 */
public class GameUtils {
    //根据图片的相对路径获取图片
    public static Image getImage(String imagePath) {
        URL url = GameUtils.class.getClassLoader().getResource(imagePath);
        BufferedImage img = null;
        try {
            img = ImageIO.read(url);
        }catch(IOException e) {
            e.printStackTrace();
        }
        return img;
    }
    //按指定的角度旋转图片
    public static Image rotateImage(final BufferedImage bufferedimage,final int degree) {
        int w = bufferedimage.getWidth();//获取图片宽度
        int h = bufferedimage.getHeight();//获取图片高度
        int type = bufferedimage.getColorModel().getTransparency();//得到图片透明度
        BufferedImage img;//空图片
        Graphics2D graphics2d;// 空的画笔。
        (graphics2d = (img = new BufferedImage(w, h, type)).createGraphics())
                .setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);// degree是整型，度数。
        graphics2d.drawImage(bufferedimage, 0, 0, null);// 从bufferedimage复制图片至img，0,0是img的坐标。
        graphics2d.dispose();
        return img;// 返回复制好的图片，原图片依然没有变，没有旋转，下次还可以使用。
    }
}
