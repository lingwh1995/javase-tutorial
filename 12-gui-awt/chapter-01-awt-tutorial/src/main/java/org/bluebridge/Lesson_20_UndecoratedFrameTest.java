package org.bluebridge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;

/**
 * @author lingwh
 * @desc 窗口修饰和自定义形状
 * @date 2026/1/30 20:33
 */
public class Lesson_20_UndecoratedFrameTest {

    public static void main(String[] args) {
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - lambda 表达式调用
        // SwingUtilities.invokeLater(() -> new _020_UndecoratedFrameTest().createAndShowGUI());

        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - 方法引用调用
        SwingUtilities.invokeLater(new Lesson_20_UndecoratedFrameTest()::createAndShowGUI);
    }

    /**
     * 创建并显示 GUI
     */
    private void createAndShowGUI() {
        // 创建窗体
        Frame frame = new Frame("I am frame") {
            /**
             * 使用匿名内部类（或者自己写个子类也行）
             * @param g the specified Graphics window
             */
            @Override
            public void paint(Graphics g) {
                g.setColor(Color.LIGHT_GRAY);
                // 先绘制标题栏
                g.fillRect(0, 0, getWidth(), 28);
                g.setColor(Color.BLACK);
                // 绘制标题名称
                g.drawString(getTitle(), getWidth() / 2, 20);
                // 原本的绘制别覆盖了，该怎么做还要怎么做
                super.paint(g);
            }
        };

        // 将窗口设定为非修饰状态 - 非修饰状态下，就只有一个窗口本身了
        frame.setUndecorated(true);
        // 设置窗体大小
        frame.setBounds(500, 500, 200, 200);

        //注意，只有窗口在非修饰状态下才能设定形状
        //这里我们使用圆角矩形，形状最好跟窗口大小一样
        frame.setShape(new RoundRectangle2D.Double(0, 0, 200, 200, 20, 20));
        frame.setVisible(true);

        // 添加鼠标监听器实现窗口拖动功能
        frame.addMouseMotionListener(new MouseMotionAdapter() {   //只需要写一个监听器就可以搞定了
            int oldX, oldY;
            public void mouseDragged(MouseEvent e) {   //鼠标拖动时如果是标题栏，就将窗口位置修改
                if(e.getY() <= 28)
                    frame.setLocation(e.getXOnScreen() - oldX, e.getYOnScreen() - oldY);
            }

            public void mouseMoved(MouseEvent e) {   //记录上一次的鼠标位置
                oldX = e.getX();
                oldY = e.getY();
            }
        });

        // 设置窗体可见
        frame.setVisible(true);
    }

}
