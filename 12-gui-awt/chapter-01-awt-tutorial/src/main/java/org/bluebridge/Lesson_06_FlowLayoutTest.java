package org.bluebridge;

import javax.swing.*;
import java.awt.*;

/**
 * @author lingwh
 * @desc 流式布局
 * @date 2025/1/26 16:32
 */
public class Lesson_06_FlowLayoutTest {

    public static void main(String[] args) {
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - lambda 表达式调用
        // SwingUtilities.invokeLater(() -> new _006_FlowLayoutTest().createAndShowGUIUseFlowLayout());

        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - 方法引用调用
        SwingUtilities.invokeLater(new Lesson_06_FlowLayoutTest()::createAndShowGUIUseFlowLayout);
    }

    /**
     * 创建并显示 GUI - 基于流式布局
     */
    private void createAndShowGUIUseFlowLayout() {
        // 创建窗体
        Frame frame = new Frame();

        // 设置窗体位置和尺寸
        frame.setBounds(500, 500, 500, 300);

        // 使用流式布局 默认居中
        //FlowLayout flowLayout = new FlowLayout();
        // 使用流式布局 指定为右对齐
        FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
//        flowLayout.setHgap(50);   //Hgap是横向间距
//        flowLayout.setVgap(50);   //Vgap是纵向间距
        frame.setLayout(flowLayout);
        frame.add(new Button("button1"));
        frame.add(new Button("button2"));
        frame.add(new Button("button3"));
        frame.add(new Button("button4"));
        frame.add(new Button("button5"));

        // 设置窗体可见
        frame.setVisible(true);
    }

}
