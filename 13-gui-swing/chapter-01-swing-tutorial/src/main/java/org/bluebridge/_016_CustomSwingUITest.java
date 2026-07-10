package org.bluebridge;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 * @author lingwh
 * @desc 自定义皮肤
 *       MetalLookAndFeel - 官方默认皮肤
 *       WindowsLookAndFeel - Windows操作系统限定皮肤，其他平台无法使用
 *       MotifLookAndFeel - 官方皮肤
 *       NimbusLookAndFeel - 官方皮肤
 *       AquaLookAndFeel - MacOS操作系统限定皮肤，其他平台无法使用
 * @date 2026/1/31 20:31
 */
public class _016_CustomSwingUITest {

    static {
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new _016_CustomSwingUITest()::createAndShowGUI);
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
        // SwingUtilities.invokeLater(() -> new _016_CustomSwingUITest().createAndShowGUI());

        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
        SwingUtilities.invokeLater(new _016_CustomSwingUITest()::createAndShowGUI);
    }

    private void createAndShowGUI() {
        // 创建窗体
        JFrame frame = new JFrame("我是窗口");
        // 设置窗体位置和尺寸
        frame.setBounds(500, 500, 500, 400);
        // 先将默认关闭行为设定为什么都不做
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        // 禁用默认布局
        frame.setLayout(null);

        // 添加一个最简单的按钮
        JButton button = new JButton("点击我");
        // 设置按钮位置和尺寸
        button.setBounds(100, 100, 100, 30);
        // 添加点击事件
        button.addActionListener(e -> JOptionPane.showMessageDialog(frame, "按钮被点击了!"));
        frame.add(button);

        frame.setVisible(true);
    }
}
