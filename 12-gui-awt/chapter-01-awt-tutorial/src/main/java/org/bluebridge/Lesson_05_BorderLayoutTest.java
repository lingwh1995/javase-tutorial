package org.bluebridge;

import java.awt.*;
import javax.swing.*;

/**
 * 方位布局
 *
 * @author lingwh
 * @date 2025/1/26 16:32
 */
public class Lesson_05_BorderLayoutTest {

    public static void main(String[] args) {
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - lambda 表达式调用
        // SwingUtilities.invokeLater(() -> new
        // _005_BorderLayoutTest().createAndShowGUIUseBorderLayout());

        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - 方法引用调用
        SwingUtilities.invokeLater(new Lesson_05_BorderLayoutTest()::createAndShowGUIUseBorderLayout);
    }

    /**
     * 创建并显示 GUI - 基于方位布局
     */
    private void createAndShowGUIUseBorderLayout() {
        // 创建窗体
        Frame frame = new Frame();

        // 设置窗体位置和尺寸
        frame.setBounds(500, 500, 500, 300);

        // 使用边界布局
        BorderLayout borderLayout = new BorderLayout();
        //        borderLayout.setHgap(50);   //Hgap是横向间距
        //        borderLayout.setVgap(50);   //Vgap是纵向间距
        frame.setLayout(borderLayout);
        frame.add(new Button("button1"), BorderLayout.WEST); // 在添加组件时，可以在后面加入约束
        frame.add(new Button("button2"), BorderLayout.EAST);
        frame.add(new Button("button3"), BorderLayout.SOUTH);
        frame.add(new Button("button4"), BorderLayout.NORTH);
        frame.add(new Button("button5"), BorderLayout.CENTER);

        // 设置窗体可见
        frame.setVisible(true);
    }
}
