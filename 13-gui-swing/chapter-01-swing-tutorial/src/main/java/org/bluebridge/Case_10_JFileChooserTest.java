package org.bluebridge;

import javax.swing.*;

/**
 * 文件选择器测试
 *
 * @author lingwh
 * @date 2026/1/31 19:17
 */
public class Case_10_JFileChooserTest {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Case_10_JFileChooserTest()::createAndShowGUI);
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
        // SwingUtilities.invokeLater(() -> new _002_FrameTest().createAndShowGUI());

        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
        SwingUtilities.invokeLater(new Case_10_JFileChooserTest()::createAndShowGUI);
    }

    private void createAndShowGUI() {
        // 创建窗体
        JFrame frame = new JFrame("我是窗口");
        // 设置窗体位置和尺寸
        frame.setBounds(500, 500, 500, 400);
        // 设置关闭操作
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建文件选择器
        JFileChooser jFileChooser = new JFileChooser();
        frame.add(jFileChooser);

        frame.setVisible(true);
    }
}
