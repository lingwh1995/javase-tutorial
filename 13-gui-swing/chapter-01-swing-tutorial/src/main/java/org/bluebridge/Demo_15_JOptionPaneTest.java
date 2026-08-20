package org.bluebridge;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

/**
 * 使用 JOptionPane 进行对话框展示窗口关闭行为
 *
 * @author lingwh
 * @date 2026/1/31 20:31
 */
public class Demo_15_JOptionPaneTest {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Demo_15_JOptionPaneTest()::createAndShowGUI);
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
        // SwingUtilities.invokeLater(() -> new _002_FrameTest().createAndShowGUI());

        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
        SwingUtilities.invokeLater(new Demo_15_JOptionPaneTest()::createAndShowGUI);
    }

    private void createAndShowGUI() {
        // 创建窗体
        JFrame frame = new JFrame("我是窗口");
        // 设置窗体位置和尺寸
        frame.setBounds(500, 500, 500, 400);
        // 先将默认关闭行为设定为什么都不做
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(
            new WindowAdapter() {
                /**
                 * 自己实现窗口关闭行为
                 *
                 * @param e the event to be processed
                 */
                @Override
                public void windowClosing(WindowEvent e) {
                    // 这里我们可以直接展示一个预设好的确认对话框
                    // int value = JOptionPane.showConfirmDialog(frame, "你真的要退出吗？");
                    int value =
                        JOptionPane.showConfirmDialog(
                            frame, "你真的要退出吗？", "提示", JOptionPane.OK_CANCEL_OPTION);
                    // 返回值就是用户的选择结果，也是预置好的，这里判断如果是OK那么就退出
                    if (value == JOptionPane.OK_OPTION) {
                        String input = JOptionPane.showInputDialog("请输入文本！");
                        System.out.println(input);
                     }
                }
        });

        frame.setVisible(true);

    }
}
