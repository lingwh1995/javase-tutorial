package org.bluebridge;

import java.awt.*;
import javax.swing.*;

/**
 * @author lingwh
 * @desc 分割面板组件
 * @date 2026/1/31 19:23
 */
public class _013_JSplitPaneTest {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new _013_JSplitPaneTest()::createAndShowGUI);
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
        // SwingUtilities.invokeLater(() -> new _002_FrameTest().createAndShowGUI());

        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
        SwingUtilities.invokeLater(new _013_JSplitPaneTest()::createAndShowGUI);
    }

    private void createAndShowGUI() {
        // 创建窗体
        JFrame frame = new JFrame("我是窗口");
        // 设置窗体位置和尺寸
        frame.setBounds(500, 500, 500, 400);
        // 设置关闭操作
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建左面板
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.ORANGE);
        leftPanel.setMinimumSize(new Dimension(150, 100)); // 设置最小宽度150
        leftPanel.setPreferredSize(new Dimension(200, 100)); // 设置首选宽度200

        // 创建右面板
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.PINK);
        rightPanel.setMinimumSize(new Dimension(200, 100)); // 设置最小宽度200
        rightPanel.setPreferredSize(new Dimension(300, 100)); // 设置首选宽度300

        // 创建分割面板
        JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        // 设置初始分割位置
        pane.setDividerLocation(150);
        frame.add(pane);

        frame.setVisible(true);
    }
}
