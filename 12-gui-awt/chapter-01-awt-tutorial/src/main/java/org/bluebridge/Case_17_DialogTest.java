package org.bluebridge;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

/**
 * 对话框
 *
 * @author lingwh
 * @date 2026/1/30 17:49
 */
public class Case_17_DialogTest {

    public static void main(String[] args) {
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - lambda 表达式调用
        // SwingUtilities.invokeLater(() -> new _017_DialoTest().createAndShowGUI());

        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - 方法引用调用
        SwingUtilities.invokeLater(new Case_17_DialogTest()::createAndShowGUI);
    }

    /**
     * 创建并显示 GUI
     */
    private void createAndShowGUI() {
        // 创建窗体
        Frame frame = new Frame();
        // 设置窗体位置和尺寸
        frame.setBounds(500, 500, 500, 300);
        // 第一个参数是父窗口或是父对话框（没错，对话框也可以由对话框唤起）
        // 最后一个参数是当对话框展示时，是否让父窗口（对话框）无法点击
        Dialog dialog = new Dialog(frame, "I am Dialog", true);
        dialog.setResizable(false);
        dialog.add(new Label("Are you sure to exit?"), BorderLayout.NORTH); // 对话框默认采用的是边界布局
        dialog.add(new Button("Yes"), BorderLayout.WEST);
        dialog.add(new Button("No"), BorderLayout.EAST);
        dialog.setBounds(500, 500, 200, 100);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialog.setVisible(true);
            }
        });
        // 设置窗体可见
        frame.setVisible(true);
    }
}
