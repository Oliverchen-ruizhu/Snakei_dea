package com.oliver.snake.distribution;

import java.awt.*;

import com.oliver.snake.util.ImageUtils;
import com.oliver.snake.constant.Constant;
/**
 * @version 0.1
 * @Author: oliver chen
 * @Description: 食物类继承于Snake
 * @Date:Create：in 2020/5/19 8:40
 * @Modified By：
 */
public class Food extends Snake{
    public Food() {
        this.isLive = true;
        this.img = ImageUtils.images.get("food");
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
        this.x = (int)(Math.random()*(Constant.GAME_WIDTH-width+10));
        this.y = (int)(Math.random()*(Constant.GAME_HIGHT-40-height)+40);
    }
    //食物被吃
    public void iseaten(MySnake mySnake) {
        if(mySnake.getrectangle().intersects(this.getrectangle())&&isLive&&mySnake.isLive) {
            this.isLive =false;//吃下食物
            mySnake.setLength(mySnake.getLength()+1);//长度+1
            mySnake.score +=10*mySnake.getLength();//加分
            if(mySnake.score%100==0) {
                mySnake.speed +=2;//速度增加
            }
        }
    }
    //绘制食物
    public void draw(Graphics g) {
        g.drawImage(img, x, y, null);
    }
}
