package org.bluebridge;

import java.awt.*;
import javax.swing.*;

/**
 * @author lingwh
 * @desc 卡片布局
 * @date 2025/1/26 16:32
 */
public class Lesson_07_CardLayoutTest {

    public static void main(String[] args) {
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - lambda 表达式调用
        // SwingUtilities.invokeLater(() -> new _007_CardLayoutTest().createAndShowGUIUseGardLayout());

        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - 方法引用调用
        SwingUtilities.invokeLater(new Lesson_07_CardLayoutTest()::createAndShowGUIUseGardLayout);
    }

    /**
     * 创建并显示 GUI - 基于卡片布局
     */
    private void createAndShowGUIUseGardLayout() {
        // 创建窗体
        Frame frame = new Frame();

        // 设置窗体位置和尺寸
        frame.setBounds(500, 500, 500, 300);

        // 使用卡片布局
        CardLayout cardLayout = new CardLayout();
        //        gridLayout.setHgap(50);   //Hgap是横向间距
        //        gridLayout.setVgap(50);   //Vgap是纵向间距
        frame.setLayout(cardLayout);
        frame.add(new Button("button1"));
        frame.add(new Button("button2"));
        frame.add(new Button("button3"));
        frame.add(new Button("button4"));
        frame.add(new Button("button5"));

        // 设置窗体可见
        frame.setVisible(true);

        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 我们需要使用CardLayout对象来进行切换
            cardLayout.next(frame);
        }
    }
}
