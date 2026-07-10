package org.bluebridge;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author lingwh
 * @desc 分割面板组件示例
 * @date 2026/1/31 19:23
 */
public class _014_JSplitPaneDemoTest {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new _014_JSplitPaneDemoTest()::createAndShowGUI);
    // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
    // SwingUtilities.invokeLater(() -> new _002_FrameTest().createAndShowGUI());

    // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
    SwingUtilities.invokeLater(new _014_JSplitPaneDemoTest()::createAndShowGUI);
  }

  private void createAndShowGUI() {
    // 创建窗体
    JFrame frame = new JFrame("我是窗口");
    // 设置窗体位置和尺寸
    frame.setBounds(500, 500, 500, 400);
    // 设置关闭操作
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // 创建分割面板
    JSplitPane pane = new JSplitPane();
    pane.setOrientation(JSplitPane.HORIZONTAL_SPLIT); // 设置为水平分割

    JTextArea area = new JTextArea(); // 右边就是我们需要编辑的文本域

    File file = new File(".idea");
    DefaultMutableTreeNode root = new DefaultMutableTreeNode(file);
    File[] files = Optional.ofNullable(file.listFiles()).orElseGet(() -> new File[0]);
    for (File f : files) root.add(new DefaultMutableTreeNode(f.getName()));
    // 左边就是我们的文件树
    JTree tree = new JTree(root);
    // 点击文件之后，我们需要变换编辑窗口中的文本内容，这里加个监听器
    tree.addTreeSelectionListener(
        e -> {
          // 先清空
          area.setText("");
          try (FileReader reader =
              new FileReader(".idea/" + e.getPath().getLastPathComponent().toString())) {
            // 直接开始读取内容
            char[] chars = new char[128];
            int len;
            while ((len = reader.read(chars)) > 0)
              // 开始写入到编辑窗口中
              area.setText(area.getText() + new String(chars, 0, len));
          } catch (IOException ex) {
            ex.printStackTrace();
          }
        });

    // 文件树和编辑区域都套一个滚动面板，因为有可能会撑得很大
    pane.setLeftComponent(new JScrollPane(tree));
    pane.setRightComponent(new JScrollPane(area));
    frame.add(pane);

    frame.setVisible(true);
  }
}
