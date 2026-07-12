package org.bluebridge;

import java.awt.*;
import javax.swing.*;

/**
 * JFrame 测试程序
 *
 * @author lingwh
 * @date 2025/8/4 18:17
 */
public class _002_FrameTest {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new _002_FrameTest()::createAndShowGUI);
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
        // SwingUtilities.invokeLater(() -> new _002_FrameTest().createAndShowGUI());

        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
        SwingUtilities.invokeLater(new _002_FrameTest()::createAndShowGUI);
    }

    /**
     * 创建并显示 GUI
     */
    private void createAndShowGUI() {
        // 创建窗体
        JFrame frame = new JFrame();
        // 设置窗体标题
        frame.setTitle("JFrame");
        // 设置窗体大小
        frame.setSize(400, 400);
        // 设置窗体关闭方式
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗体背景色
        frame.setBackground(Color.YELLOW);
        // 设置内容面板背景色
        frame.getContentPane().setBackground(Color.YELLOW);
        // 是否可以调节窗体大小
        frame.setResizable(false);
        // 设置窗体是否一致展示在最前端
        frame.setAlwaysOnTop(true);
        // 设置窗体位置
        frame.setLocation(0, 0);

        // 设置窗体居中显示 - 方式一
        frame.setLocationRelativeTo(null);

        // 获取屏幕尺寸 - 方式二
        /*
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // 获取窗体 尺寸
        int windowWidth = frame.getWidth();
        int windowHeight = frame.getHeight();

        // 计算居中位置
        int x = (screenWidth - windowWidth) / 2;
        int y = (screenHeight - windowHeight) / 2;

        // 设置窗体位置
        frame.setLocation(x, y);
        */

        // 一次性设置窗体位置和大小
        // frame.setBounds(0, 0, 400, 400);

        // 设置窗体中鼠标的样式
        frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // 让窗体可见
        frame.setVisible(true);
    }
}
