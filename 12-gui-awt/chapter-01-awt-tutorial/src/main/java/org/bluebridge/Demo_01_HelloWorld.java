package org.bluebridge;

import java.awt.*;
import javax.swing.*;

/**
 * AWT HelloWorld程序
 *
 * @author lingwh
 * @date 2025/8/4 18:11
 */
public class Demo_01_HelloWorld {

    public static void main(String[] args) {
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - lambda 表达式调用
        // SwingUtilities.invokeLater(() -> new _001_HelloWorld().createAndShowGUI());

        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - 方法引用调用
        SwingUtilities.invokeLater(new Demo_01_HelloWorld()::createAndShowGUI);
    }

    private void createAndShowGUI() {
        // 创建窗体
        Frame frame = new Frame();
        // 设置窗体的标题
        frame.setTitle("HelloWorld");
        // 设置窗体的大小
        frame.setSize(300, 200);
        // 显示窗体
        frame.setVisible(true);
    }
}
