package org.bluebridge;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * @author lingwh
 * @desc Swing HelloWorld程序
 * @date 2025/8/4 18:17
 */
public class $_01_HelloWorld {

  public static void main(String[] args) {
    // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
    // SwingUtilities.invokeLater(() -> new HelloWorld().createAndShowGUI());

    // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
    SwingUtilities.invokeLater(new $_01_HelloWorld()::createAndShowGUI);
  }

    /**
     * 创建并显示 GUI
     */
    private void createAndShowGUI() {
        // 创建 JFrame 实例
        JFrame frame = new JFrame("First Swing App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        // 让窗口居中显示
        frame.setLocationRelativeTo(null);

    // 创建组件
    JLabel label = new JLabel("欢迎使用Swing!", JLabel.CENTER);
    JButton button = new JButton("点击我");

    // 添加事件监听器
    button.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(frame, "按钮被点击了!");
          }
        });

    // 布局管理
    frame.setLayout(new java.awt.BorderLayout());
    frame.add(label, java.awt.BorderLayout.CENTER);
    frame.add(button, java.awt.BorderLayout.SOUTH);

    // 显示窗口
    frame.setVisible(true);
  }
}
