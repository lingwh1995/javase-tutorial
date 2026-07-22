package org.bluebridge;

import javax.swing.*;

/**
 * JToggleButton 切换按钮
 *
 * @author lingwh
 * @date 2026/1/31 18:12
 */
public class Case_06_JToggleButtonTest {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Case_06_JToggleButtonTest()::createAndShowGUI);
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
        // SwingUtilities.invokeLater(() -> new _002_FrameTest().createAndShowGUI());

        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
        SwingUtilities.invokeLater(new Case_06_JToggleButtonTest()::createAndShowGUI);
    }

    private void createAndShowGUI() {
        // 创建窗体
        JFrame frame = new JFrame("我是窗口");
        // 设置窗体的大小
        frame.setSize(500, 300);
        // 设置关闭操作
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置为绝对定位布局
        frame.setLayout(null);

        // 开关按钮有两个状态，一个是开一个是关
        JToggleButton button = new JToggleButton("我是切换按钮");
        button.setBounds(20, 50, 100, 30);
        // 设置默认选中状态为 true
        button.setSelected(true);
        // 将按钮添加到窗体中
        frame.add(button);

        frame.setVisible(true);
    }
}
