package org.bluebridge;

import java.awt.*;
import javax.swing.*;

/**
 * 表格布局案例四 - 上半部分面板设定为流式布局，下半部分面板设定为网格布局
 *
 * @author lingwh
 * @date 2026/1/29 21:06
 */
public class Demo_12_GridLayout_4 {

    public static void main(String[] args) {
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - lambda 表达式调用
        // SwingUtilities.invokeLater(() -> new
        // _012_GridLayout_Demo_4().createAndShowGUIUseGridLayout());

        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - 方法引用调用
        SwingUtilities.invokeLater(new Demo_12_GridLayout_4()::createAndShowGUIUseGridLayout);
    }

    /**
     * 创建并显示 GUI - 基于流式布局
     */
    private void createAndShowGUIUseGridLayout() {
        // 创建窗体
        Frame frame = new Frame();

        // 设置窗体位置和尺寸
        frame.setBounds(500, 500, 500, 300);

        // 上半部分面板 - 流式布局
        Panel top = new Panel();
        top.setBackground(Color.PINK);
        // 面板默认就是FlowLayout，此处可省略
        top.setLayout(new FlowLayout());
        for (int i = 0; i < 5; i++) {
            top.add(new Button("FLOW" + i));
        }
        // 指定添加到窗口的北部（上方）
        frame.add(top, BorderLayout.NORTH);

        // 下半部分面板 - 网格布局
        Panel bottom = new Panel();
        bottom.setBackground(Color.ORANGE);
        // 补充GridLayout的行列参数（无参构造默认1行n列） 0行2列，行数自动适配
        bottom.setLayout(new GridLayout(0, 2));
        for (int i = 0; i < 5; i++) {
            bottom.add(new Button("GRID" + i));
        }
        // 指定添加到窗口的中部（下方）
        frame.add(bottom, BorderLayout.CENTER);

        // 设置窗体可见
        frame.setVisible(true);
    }
}
