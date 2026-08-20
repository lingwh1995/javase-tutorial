package org.bluebridge;

import java.awt.*;
import javax.swing.*;

/**
 * 表格布局
 *
 * @author lingwh
 * @date 2025/1/26 16:32
 */
public class Demo_08_GridLayoutTest {

    public static void main(String[] args) {
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - lambda 表达式调用
        // SwingUtilities.invokeLater(() -> new _008_GridLayoutTest().createAndShowGUIUseGridLayout());

        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - 方法引用调用
        SwingUtilities.invokeLater(new Demo_08_GridLayoutTest()::createAndShowGUIUseGridLayout);
    }

    /**
     * 创建并显示 GUI - 基于表格布局
     */
    private void createAndShowGUIUseGridLayout() {
        // 创建窗体
        Frame frame = new Frame();
        // 设置窗体位置和尺寸
        frame.setBounds(500, 500, 500, 300);
        // 使用表格布局(默认为一行一列布局)
        GridLayout gridLayout = new GridLayout();
        // 设置2行3列布局
        gridLayout = new GridLayout(2, 3);
        // 或者使用 setRows 和 setColumns 方法设置行数和列数
        //        gridLayout.setRows(2);
        //        gridLayout.setColumns(3);

        //        gridLayout.setHgap(50);   //Hgap是横向间距
        //        gridLayout.setVgap(50);   //Vgap是纵向间距
        frame.setLayout(gridLayout);
        frame.add(new Button("button1"));
        frame.add(new Button("button2"));
        frame.add(new Button("button3"));
        frame.add(new Button("button4"));
        frame.add(new Button("button5"));
        // 设置窗体可见
        frame.setVisible(true);
    }
}
