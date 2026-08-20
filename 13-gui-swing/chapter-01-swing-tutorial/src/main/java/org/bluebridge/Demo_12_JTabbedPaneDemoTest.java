package org.bluebridge;

import javax.swing.*;

/**
 * 标签页组件
 *
 * @author lingwh
 * @date 2026/1/31 19:05
 */
public class Demo_12_JTabbedPaneDemoTest {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Demo_12_JTabbedPaneDemoTest()::createAndShowGUI);
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
        // SwingUtilities.invokeLater(() -> new _002_FrameTest().createAndShowGUI());

        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
        SwingUtilities.invokeLater(new Demo_12_JTabbedPaneDemoTest()::createAndShowGUI);
    }

    private void createAndShowGUI() {
        // 创建窗体
        JFrame frame = new JFrame("我是窗口");
        // 设置窗体位置和尺寸
        frame.setBounds(500, 500, 500, 400);
        // 设置关闭操作
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建标签页组件
        JTabbedPane pane = new JTabbedPane();
        pane.setBounds(0, 0, 500, 300);
        // 一号面板当颜色选择器
        pane.addTab("一号", new JColorChooser());
        // 二号面板当文件选择器
        pane.addTab("二号", new JFileChooser());
        frame.add(pane);

        frame.setVisible(true);
    }
}
