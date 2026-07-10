package org.bluebridge;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * @author lingwh
 * @desc 自定义按钮UI样式
 * @date 2026/1/31 20:31
 */
public class _017_CustomButtonUITest {

  public static void main(String[] args) {
    // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
    // SwingUtilities.invokeLater(() -> new _017_CustomButtonUITest().createAndShowGUI());

    // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
    SwingUtilities.invokeLater(new _017_CustomButtonUITest()::createAndShowGUI);
  }

    /**
     * 创建并显示 GUI
     */
    private void createAndShowGUI() {
        // 创建 JFrame 实例
        JFrame frame = new JFrame("Swing HelloWorld");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        // 让窗口居中显示
        frame.setLayout(null);

    // 添加一个最简单的按钮
    JButton button = new JButton("点击我");
    // 设置按钮的位置和大小
    button.setBounds(10, 20, 100, 30);
    // 添加点击事件
    button.addActionListener(e -> JOptionPane.showMessageDialog(frame, "按钮被点击了!"));
    // 设置按钮的UI样式为自定义的按钮UI样式
    button.setUI(new CustomButtonUI());
    frame.add(button);

    // 显示窗口
    frame.setVisible(true);
  }
}

/**
 * 自定义按钮UI样式
 */
class CustomButtonUI extends BasicButtonUI {
  /**
   * 重写对应UI的paint方法就可以了
   *
   * @param g
   * @param c
   */
  @Override
  public void paint(Graphics g, JComponent c) {
    int width = c.getWidth(), height = c.getHeight();
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, width, height);
    g.setColor(Color.WHITE);
    JButton button = (JButton) c;
    g.drawString(button.getText(), 0, 20);
  }
}
