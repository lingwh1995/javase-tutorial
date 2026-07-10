package org.bluebridge;

import javax.swing.*;

/**
 * @author lingwh
 * @desc JComponent工具提示文本
 * @date 2026/1/31 18:42
 */
public class _008_JComponent_ToolTipText_Test {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new _008_JComponent_ToolTipText_Test()::createAndShowGUI);
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
        // SwingUtilities.invokeLater(() -> new _002_FrameTest().createAndShowGUI());

        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
        SwingUtilities.invokeLater(new _008_JComponent_ToolTipText_Test()::createAndShowGUI);
    }

    private void createAndShowGUI() {
        // 创建窗体
        JFrame frame = new JFrame("我是窗口");
        // 设置窗体位置和尺寸
        frame.setBounds(500, 500, 500, 400);
        // 设置关闭操作
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置为绝对定位布局
        frame.setLayout(null);

        // 创建组件
        JButton button = new JButton("我是按钮");
        button.setBounds(50, 50, 100, 30);
        // 创建组件说明文字
        button.setToolTipText("这个按钮是用来解决你毕设的！");
        frame.add(button);

        frame.setVisible(true);
    }
}
