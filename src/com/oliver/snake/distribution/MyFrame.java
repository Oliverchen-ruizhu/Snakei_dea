package com.oliver.snake.distribution;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.oliver.snake.constant.*;
/**
 * @version 0.1
 * @Author: oliver chen
 * @Description: 加载窗体
 * @Date:Create：in 2020/5/19 8:41
 * @Modified By：
 */
public class MyFrame extends Frame {
    //加载窗体
    public void loadframe() {
        this.setTitle("贪吃蛇");//标题
        this.setSize(Constant.GAME_WIDTH,Constant.GAME_HIGHT);//尺寸
        this.setBackground(Color.BLACK);//背景色
        this.setLocationRelativeTo(null);//居中
        //重写叉选
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setVisible(true);
        new MyThread().start();
    }
    // 防止图片闪烁，使用双重缓存
    Image backImg = null;
    @Override
    public void update(Graphics g) {
        if (backImg == null) {
            backImg = createImage(Constant.GAME_WIDTH, Constant.GAME_HIGHT);
        }
        Graphics backg = backImg.getGraphics();
        Color c = backg.getColor();
        backg.setColor(Color.BLACK);
        backg.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HIGHT);
        backg.setColor(c);
        paint(backg);
        g.drawImage(backImg, 0, 0, null);
    }
    //不断重绘的线程内部类
    class MyThread extends Thread{
        public void run(){
            while(true) {
                repaint();//重新绘制
                try {
                    sleep(25);//每25s重绘一次
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}

