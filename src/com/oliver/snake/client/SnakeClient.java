package com.oliver.snake.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.oliver.snake.distribution.Food;
import com.oliver.snake.distribution.MyFrame;
import com.oliver.snake.distribution.MySnake;
import com.oliver.snake.help.Help;
import com.oliver.snake.util.ImageUtils;
/**
 * @Author: oliver chen
 * @Description: 程序运行的入口
 * @Date:Create：in 2020/5/19 8:34
 * @Modified By：
 * @version 0.1
 */
public class SnakeClient extends MyFrame{
    public MySnake mySnake = new MySnake(100,100);//蛇
    public Food food = new Food();//食物
    Image background  = ImageUtils.images.get("background");//背景
    Image fail = ImageUtils.images.get("fail");//游戏结束
    Image stop = ImageUtils.images.get("stop");//暂停
    public void loadframe() {
        super.loadframe();
        MenuBar mb = new MenuBar();
        Menu m1 = new Menu("Game");
        //m1.addSeparator ();
        Menu m2 = new Menu("Help");
        this.setMenuBar(mb);
        mb.add(m1);
        mb.add(m2);
        MenuItem m11 = new MenuItem("GameStart");
        m1.add(m11);
        m1.addSeparator();
        MenuItem m12 = new MenuItem("Exit");
        m1.add(m12);
        MenuItem m21 = new MenuItem("UserDirections");
        m2.add(m21);
        ActionListener menuListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if (cmd.equals("Exit")) {
                    System.exit(0);
                }else if (cmd.equals("GameStart")) {
                    mySnake.isStart=true;
                    mySnake.isLive =true;
                    mySnake.x = 100;
                    mySnake.y = 100;
                    mySnake.bodylist.removeAll(mySnake.bodylist);
                    mySnake.setLength(1);
                    mySnake.score=0;
                    mySnake.speed=5;
                    mySnake.setDirection(false,false,false,true);//默认向右
                    mySnake.newImgSnakeHead=(BufferedImage) ImageUtils.images.get("snake_head");
                    food.isLive=true;
                }else if(cmd.equals("UserDirections")) {
                    new Help().loadframe();
                }
            }
        };
        m11.addActionListener(menuListener);
        m12.addActionListener(menuListener);
        m21.addActionListener(menuListener);
        //处理键盘事件
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                mySnake.keyPressed(e);//委托给mysnake
            }
        });

    }
    //绘制界面
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, null);//背景
        //System.out.println(food.isLive);
        if(mySnake.isLive) {
            mySnake.draw(g);
            if(food.isLive) {
                food.draw(g);
                food.iseaten(mySnake);
            }else {
                food = new Food();
            }
            if(mySnake.isStop) {
                super.setTitle("贪吃蛇（暂停）");
                g.drawImage(stop, (background.getWidth(null)-fail.getWidth(null))/2+170, (background.getHeight(null)-fail.getHeight(null))/2+20,null);
            }else {
                super.setTitle("贪吃蛇");
            }
        }else{
            g.drawImage(fail,(background.getWidth(null)-fail.getWidth(null))/2, (background.getHeight(null)-fail.getHeight(null))/2,null);
        }
        drawScore(g);
    }
    //绘制分数
    private void drawScore(Graphics g) {
        g.setFont(new Font("Courier New",Font.BOLD,40));
        g.setColor(Color.RED);
        g.drawString("Score:"+mySnake.score, 700, 100);
    }
    public static void main(String[] args) {
        new SnakeClient().loadframe();
    }
}
