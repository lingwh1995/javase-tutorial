package org.bluebridge;

import java.awt.*;
import javax.swing.*;

/**
 * @author lingwh
 * @desc 标签页组件
 * @date 2026/1/31 19:05
 */
public class _011_JTabbedPaneTest {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new _011_JTabbedPaneTest()::createAndShowGUI);
    // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
    // SwingUtilities.invokeLater(() -> new _002_FrameTest().createAndShowGUI());

    // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
    SwingUtilities.invokeLater(new _011_JTabbedPaneTest()::createAndShowGUI);
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
    pane.addTab(
        "一号",
        new JPanel() {
          {
            setBackground(Color.ORANGE);
          }
        });
    pane.addTab(
        "二号",
        new JPanel() {
          {
            setBackground(Color.PINK);
          }
        });
    frame.add(pane);

    frame.setVisible(true);
  }
}
