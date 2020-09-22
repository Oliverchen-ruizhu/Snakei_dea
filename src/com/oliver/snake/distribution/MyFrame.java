package com.oliver.snake.distribution;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.oliver.snake.constant.*;
/**
 * @version 0.1
 * @Author: oliver chen
 * @Description: ���ش���
 * @Date:Create��in 2020/5/19 8:41
 * @Modified By��
 */
public class MyFrame extends Frame {
    //���ش���
    public void loadframe() {
        this.setTitle("̰����");//����
        this.setSize(Constant.GAME_WIDTH,Constant.GAME_HIGHT);//�ߴ�
        this.setBackground(Color.BLACK);//����ɫ
        this.setLocationRelativeTo(null);//����
        //��д��ѡ
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setVisible(true);
        new MyThread().start();
    }
    // ��ֹͼƬ��˸��ʹ��˫�ػ���
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
    //�����ػ���߳��ڲ���
    class MyThread extends Thread{
        public void run(){
            while(true) {
                repaint();//���»���
                try {
                    sleep(25);//ÿ25s�ػ�һ��
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}

