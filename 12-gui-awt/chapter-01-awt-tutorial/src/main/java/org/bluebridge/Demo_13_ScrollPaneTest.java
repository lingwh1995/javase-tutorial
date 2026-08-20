package org.bluebridge;

import java.awt.*;
import javax.swing.*;

/**
 * 带有滚动条的面板/滚动面板
 *
 * @author lingwh
 * @date 2026/1/29 22:01
 */
public class Demo_13_ScrollPaneTest {

    public static void main(String[] args) {
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - lambda 表达式调用
        // SwingUtilities.invokeLater(() -> new _013_ScrollPaneTest().createAndShowGUI());

        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - 方法引用调用
        SwingUtilities.invokeLater(new Demo_13_ScrollPaneTest()::createAndShowGUI);
    }

    /**
     * 创建并显示 GUI
     */
    private void createAndShowGUI() {
        // 创建窗体
        Frame frame = new Frame();
        // 设置窗体位置和尺寸
        frame.setBounds(500, 500, 500, 300);
        // 创建滚动面板
        ScrollPane scrollPane = new ScrollPane();
        frame.add(scrollPane);
        // 创建滚动面板内部的要展示的面板
        GridLayout layout = new GridLayout();
        layout.setRows(20);
        Panel panel = new Panel();
        panel.setLayout(layout);
        for (int i = 0; i < 20; i++) {
            Button button = new Button("BUTTON" + i);
            // 设置首选大小
            button.setPreferredSize(new Dimension(100, 50));
            // 为面板添加按钮
            panel.add(button);
        }
        // 添加面板
        scrollPane.add(panel);
        // 设置窗体可见
        frame.setVisible(true);
    }
}
