package org.bluebridge;

import javax.swing.*;

/**
 * 进度条
 *
 * @author lingwh
 * @date 2026/1/31 17:33
 */
public class Case_04_JProgressBarTest {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Case_04_JProgressBarTest()::createAndShowGUI);
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
        // SwingUtilities.invokeLater(() -> new _002_FrameTest().createAndShowGUI());

        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
        SwingUtilities.invokeLater(new Case_04_JProgressBarTest()::createAndShowGUI);
    }

    private void createAndShowGUI() {
        // 创建窗体
        JFrame frame = new JFrame("我是窗口");
        // 设置窗体的大小
        frame.setSize(500, 300);

        // 设置布局管理器
        frame.setLayout(null);

        // 创建进度条
        JProgressBar bar = new JProgressBar();
        // 设定进度条的最大值
        bar.setMaximum(100);
        // 设定进度值
        bar.setValue(50);
        bar.setBounds(20, 50, 100, 10);
        frame.add(bar);

        frame.setVisible(true);
    }
}
