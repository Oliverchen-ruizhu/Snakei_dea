package com.oliver.snake.util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 0.1
 * @Author: oliver chen
 * @Description: 获取图片文件
 * @Date:create：in 2020/5/19 0:48
 * @Modified By：
 */

public class ImageUtils {

    public static Map<String, Image> images = new HashMap<>();
    static {
        try {
            images.put("snake_body",GameUtils.getImage("com/oliver/snake/image/snake_body.png"));
            images.put("food",GameUtils.getImage("com/oliver/snake/image/food.png"));
            images.put("snake_head",GameUtils.getImage("com/oliver/snake/image/snake_head.png"));
            images.put("background",GameUtils.getImage("com/oliver/snake/image/background.bmp"));
            images.put("fail",GameUtils.getImage("com/oliver/snake/image/fail.bmp"));
            images.put("stop",GameUtils.getImage("com/oliver/snake/image/stop.png"));
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
