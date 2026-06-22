package org.bluebridge;

import javax.swing.*;
import java.awt.*;

/**
 * @author lingwh
 * @desc 表格布局案例二 - 把容器分成两行，每一行里面都有几个按钮
 * @date 2026/1/29 21:06
 */
public class Lesson_10_GridLayout_Demo_2 {

    public static void main(String[] args) {
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - lambda 表达式调用
        // SwingUtilities.invokeLater(() -> new _010_GridLayout_Demo_2().createAndShowGUIUseGridLayout());

        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - 方法引用调用
        SwingUtilities.invokeLater(new Lesson_10_GridLayout_Demo_2()::createAndShowGUIUseGridLayout);
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
        // 设置行数为2，一会就会分成2行了
        layout.setRows(2);
        frame.setLayout(layout);

        Panel top = new Panel();
        top.setBackground(Color.PINK);
        frame.add(top);
        // 面板就像窗口一样，可以设定布局和添加组件
        for (int i = 0; i < 5; i++){
            top.add(new Button("FLOW" + i));
        }
        frame.add(top);

        Panel bottom = new Panel();
        bottom.setBackground(Color.ORANGE);
        frame.add(bottom);
        for (int i = 0; i < 5; i++) {
            bottom.add(new Button("GRID" + i));
        }
        frame.add(bottom);

        // 设置窗体可见
        frame.setVisible(true);
    }

}
