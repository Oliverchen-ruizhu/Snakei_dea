package com.oliver.snake.help;

import javax.swing.*;
import java.awt.*;

/**
 * @version 0.1
 * @Author: oliver chen
 * @Description: 使用帮助
 * @Date:Create：in 2020/5/19 0:46
 * @Modified By：
 */

public class Help extends JFrame {
        public static boolean tag = false;
        public void loadframe() {
            this.setTitle("使用说明");
            this.setSize(470,200);//尺寸
            this.setBackground(Color.white);//背景色
            this.setLocationRelativeTo(null);//居中
//		this.setDefaultCloseOperation();
            this.setDefaultCloseOperation(2);

            //设置文本内容
            JLabel jl = new JLabel("使用帮助：蛇的方向控制↑：上↓：下←：左→：右.空格键：暂停与恢复",JLabel.CENTER);
            //文字颜色

            jl.setForeground(Color.GRAY);

            jl.setBounds(50,50,280,30);

            this.add(jl);
            if(!tag)this.setVisible(true);
            else this.setVisible(false);
        }


}
