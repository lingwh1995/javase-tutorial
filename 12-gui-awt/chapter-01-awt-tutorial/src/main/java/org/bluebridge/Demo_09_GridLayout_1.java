package org.bluebridge;

import java.awt.*;
import javax.swing.*;

/**
 * 表格布局案例一 - 把容器分成三行
 *
 * @author lingwh
 * @date 2026/1/29 21:06
 */
public class Demo_09_GridLayout_1 {

    public static void main(String[] args) {
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - lambda 表达式调用
        // SwingUtilities.invokeLater(() -> new
        // _009_GridLayout_Demo_1().createAndShowGUIUseGridLayout());

        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - 方法引用调用
        SwingUtilities.invokeLater(new Demo_09_GridLayout_1()::createAndShowGUIUseGridLayout);
    }

    /**
     * 创建并显示 GUI - 基于流式布局
     */
    private void createAndShowGUIUseGridLayout() {
        // 创建窗体
        Frame frame = new Frame();

        // 设置窗体位置和尺寸
        frame.setBounds(500, 500, 500, 300);

        // 先设置整个窗口的布局
        GridLayout layout = new GridLayout();
        // 设置行数为3，一会就会分成3行了
        layout.setRows(3);
        frame.setLayout(layout);

        /**
         * 顶部
         */
        // 接着我们创建一下上半部分的面板和下半部分的面板
        Panel top = new Panel();
        // 添加一个背景颜色方便区分
        top.setBackground(Color.PINK);
        frame.add(top);

        /**
         * 中部
         */
        Panel middle = new Panel();
        // 添加一个背景颜色方便区分
        middle.setBackground(Color.GREEN);
        frame.add(middle);

        /**
         * 下部
         */
        Panel bottom = new Panel();
        // 添加一个背景颜色方便区分
        bottom.setBackground(Color.ORANGE);
        frame.add(bottom);

        // 设置窗体可见
        frame.setVisible(true);
    }
}
