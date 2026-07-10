package org.bluebridge;

import javax.swing.*;

/**
 * @author lingwh
 * @desc 颜色选择器
 * @date 2026/1/31 18:30
 */
public class _007_JColorChooserTest {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new _007_JColorChooserTest()::createAndShowGUI);
    // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
    // SwingUtilities.invokeLater(() -> new _002_FrameTest().createAndShowGUI());

    // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
    SwingUtilities.invokeLater(new _007_JColorChooserTest()::createAndShowGUI);
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

    // 开关按钮有两个状态，一个是开一个是关
    JColorChooser chooser = new JColorChooser();
    chooser.setBounds(0, 0, 300, 300);
    frame.add(chooser);

    frame.setVisible(true);
  }
}
