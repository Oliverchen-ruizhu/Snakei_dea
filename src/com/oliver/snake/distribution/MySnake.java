package com.oliver.snake.distribution;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import com.oliver.snake.util.ImageUtils;
import com.oliver.snake.util.GameUtils;
import com.oliver.snake.constant.Constant;
/**
 * @version 0.1
 * @Author: oliver chen
 * @Description: 子蛇类
 * @Date:Create：in 2020/5/19 8:42
 * @Modified By：
 */
public class MySnake extends Snake implements Moveable{

    //蛇头
    private static final BufferedImage IMG_SNAKE_HEAD =(BufferedImage) ImageUtils.images.get("snake_head");
    public int speed;//移速
    //暂停
    public boolean isStop = false;
    //重启
    public boolean isRestart = false;
    public boolean isStart = false;//开始
    private int length;//蛇长
    private int num;
    public static List<Point> bodylist =  new LinkedList();//储存蛇的轨迹点
    public int score =0;//得分
    public static BufferedImage newImgSnakeHead;//方向改变后的蛇头
    boolean up,down,left,right=true;//默认向右
    boolean [] tag = {up,down,left,right};
    public MySnake(int x,int y) {
        this.isLive =true;
        this.x = x;
        this.y = y;
        this.img = ImageUtils.images.get("snake_body");
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
        this.speed = 5;
        this.length = 1;
        this.num = width/speed;
        newImgSnakeHead = IMG_SNAKE_HEAD;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public void setDirection(boolean up,boolean down,boolean left,boolean right) {
        this.up=up;
        this.down=down;
        this.left=left;
        this.right=right;
    }
    //键盘事件
    public void keyPressed(KeyEvent e) {
        if(isStart) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_UP://上箭头
                    if(!down&&!isStop) {//不能反向移动
                        up=true;
                        down = false;
                        left = false;
                        right = false;
                        newImgSnakeHead = (BufferedImage) GameUtils.rotateImage(IMG_SNAKE_HEAD, -90);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(!up&&!isStop) {
                        up=false;
                        down = true;
                        left = false;
                        right = false;
                        newImgSnakeHead = (BufferedImage) GameUtils.rotateImage(IMG_SNAKE_HEAD, 90);
                        boolean [] tag = {up,down,left,right};
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if(!right&&!isStop) {
                        up=false;
                        down = false;
                        left = true;
                        right = false;
                        newImgSnakeHead = (BufferedImage) GameUtils.rotateImage(IMG_SNAKE_HEAD, -180);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(!left&&!isStop) {
                        up=false;
                        down = false;
                        left = false;
                        right = true;
                        newImgSnakeHead = IMG_SNAKE_HEAD;
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    if(!isStop) {
                        this.isStop = true;
                        tag[0]=up;
                        tag[1]=down;
                        tag[2]=left;
                        tag[3]=right;
                        up=false;
                        down = false;
                        left = false;
                        right = false;
                    }else {
                        this.isStop=false;
                        this.isRestart=true;
                        up=tag[0];
                        down=tag[1];
                        left=tag[2];
                        right=tag[3];
                    }
                    break;
            }
        }
    }
    //移动
    public void move() {
        if(isStart) {
            if(up) y -=speed;
            else if(down) y +=speed;
            else if(left) x -=speed;
            else if(right)x +=speed;
        }
    }
    //绘制
    public void draw(Graphics g) {
        outOfBounds();//处理出界问题
        eatBody();
        if(!this.isStop)
            bodylist.add(new Point(x,y));//保存轨迹
        //当轨迹点个数为蛇长+1的num倍时移除第一个(移去栈底元素),num = width/speed
        if(bodylist.size()==(this.length+1)*num&&!this.isStop)bodylist.remove(0);
        g.drawImage(newImgSnakeHead,x,y,null);//画蛇头
        drawBody(g);
        move();
    }
    //画蛇身
    private void drawBody(Graphics g) {

        int length = bodylist.size()-1-num;//前num个存储的是蛇头的当前轨迹坐标
        for(int i=length;i>num;i-=num) {// 尾部添加
            Point p = bodylist.get(i);
            g.drawImage(img, p.x, p.y,null);

        }
    }
    //是否吃到自身
    private void eatBody() {
        if(isStart) {
            if(this.isRestart) {
                for(Point point1:bodylist) {
                    for(Point point2:bodylist) {
                        if((point1.equals(point2)&&point1!=point2))this.isLive=false;
                    }
                }
            }
        }
    }
    //出界
    private void outOfBounds() {
        boolean xOut = (x<=0||x>=(Constant.GAME_WIDTH-width));
        boolean yOut = (y<=0||y>=(Constant.GAME_HIGHT-height));
        if(xOut||yOut)this.isLive =false;
    }
}

