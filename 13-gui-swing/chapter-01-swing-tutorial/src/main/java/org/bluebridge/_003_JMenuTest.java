package org.bluebridge;

import javax.swing.*;

/**
 * @author lingwh
 * @desc 创建一个JMenu
 * @date 2026/1/31 17:25
 */
public class _003_JMenuTest {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new _003_JMenuTest()::createAndShowGUI);
    // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
    // SwingUtilities.invokeLater(() -> new _002_FrameTest().createAndShowGUI());

    // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
    SwingUtilities.invokeLater(new _003_JMenuTest()::createAndShowGUI);
  }

  private void createAndShowGUI() {
    // 创建窗体
    JFrame frame = new JFrame("我是窗口");
    // 设置窗体的大小
    frame.setSize(500, 300);

    // JMenuBar对应的就是MenuBar
    JMenuBar bar = new JMenuBar();

    JMenu menu = new JMenu("我是菜单");
    menu.add(new JMenuItem("选项1"));
    menu.add(new JMenuItem("选项2"));
    bar.add(menu);
    frame.setJMenuBar(bar);

    frame.setVisible(true);
  }
}
