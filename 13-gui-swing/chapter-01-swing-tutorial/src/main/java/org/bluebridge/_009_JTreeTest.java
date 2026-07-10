package org.bluebridge;

import java.io.File;
import java.util.Optional;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author lingwh
 * @desc 树形组件
 * @date 2026/1/31 18:56
 */
public class _009_JTreeTest {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new _009_JTreeTest()::createAndShowGUI);
    // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
    // SwingUtilities.invokeLater(() -> new _002_FrameTest().createAndShowGUI());

    // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
    SwingUtilities.invokeLater(new _009_JTreeTest()::createAndShowGUI);
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

    // 这里我们让JTree展示.idea目录下所有文件
    File file = new File(".idea");
    // 既然是树形关系，肯定有一个根结点
    DefaultMutableTreeNode root = new DefaultMutableTreeNode(file.getName());
    // 拿到当前目录下所有文件和文件夹
    File[] files = Optional.ofNullable(file.listFiles()).orElseGet(() -> new File[0]);
    for (File f : files) {
      // 构造子结点并连接
      root.add(new DefaultMutableTreeNode(f.getName()));
    }

    // 设定默认的根结点
    JTree tree = new JTree(root);
    tree.setBounds(0, 0, 200, 200);
    frame.add(tree);

    frame.setVisible(true);
  }
}
