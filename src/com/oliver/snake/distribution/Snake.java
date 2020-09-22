package com.oliver.snake.distribution;

import java.awt.*;

/**
 * @version 0.1
 * @Author: oliver chen
 * @Description: 父蛇类
 * @Date:Create：in 2020/5/19 8:43
 * @Modified By：
 */
public abstract class Snake implements Drawable{
    public int x;//横纵坐标
    public int y;
    Image img;
    int width,height;//图片的长和宽
    public boolean isLive;//存活状态
    public abstract void draw(Graphics g);
    //获取图片对应的矩阵
    public Rectangle getrectangle() {
        return new Rectangle(x,y,width,height);
    }
}
