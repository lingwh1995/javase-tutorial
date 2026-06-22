package org.bluebridge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author lingwh
 * @desc 事件监听器
 * @date 2025/8/4 18:17
 */
public class Lesson_03_EventListenerTest {

    public static void main(String[] args) {
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - lambda 表达式调用
        // SwingUtilities.invokeLater(() -> new _003_EventListenerTest().createAndShowGUI());

        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - 方法引用调用
        SwingUtilities.invokeLater(new Lesson_03_EventListenerTest()::createAndShowGUI);
    }

    /**
     * 创建并显示 GUI
     */
    private void createAndShowGUI() {
        // 创建窗体
        Frame frame = new Frame();

        // 设置窗体标题
        frame.setTitle("EventListener");

        // 设置窗体大小
        frame.setSize(400, 400);

        /**
         * 添加窗体监听器
         */
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("窗体打开了......");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("收到关闭请求，正在退出程序......");
                // 释放窗体资源
                frame.dispose();
                // 退出虚拟机（如果是主程序）
                //System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("窗体关闭了......");
            }

            @Override
            public void windowIconified(WindowEvent e) {
                System.out.println("窗体最小化了......");
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                System.out.println("窗体从最小化变成普通状态了......");
            }
        });

        /**
         * 添加键盘监听器
         */
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("按下了键 keyChar ：" + (int)e.getKeyChar());
            }
        });

        /**
         * 添加鼠标监听器
         */
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("鼠标被点击了......");
                System.out.println("鼠标位置：x = " + e.getX() + " , y = " + e.getY());
                int button = e.getButton();
                switch (button) {
                    case MouseEvent.BUTTON1:
                        System.out.println("鼠标左键被点击了......");
                        break;
                    case MouseEvent.BUTTON2:
                        System.out.println("鼠标滚轮被点击了......");
                        break;
                    case MouseEvent.BUTTON3:
                        System.out.println("鼠标右键被点击了......");
                        break;
                    default:
                        break;
                }
            }
        });

        /**
         * 添加鼠标滚轮监听器
         */
        frame.addMouseWheelListener(e -> {
            // 获取滚动数量
            System.out.println("鼠标滚轮被滚动了......" + e.getScrollAmount());
        });

        // 设置窗体可见
        frame.setVisible(true);
    }

}
