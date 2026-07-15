package org.bluebridge;

import java.awt.*;
import javax.swing.*;

/**
 * 列表
 *
 * @author lingwh
 * @date 2026/1/29 22:18
 */
public class Lesson_14_ListTest {

    public static void main(String[] args) {
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - lambda 表达式调用
        // SwingUtilities.invokeLater(() -> new _014_ListTest().createAndShowGUI());

        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - 方法引用调用
        SwingUtilities.invokeLater(new Lesson_14_ListTest()::createAndShowGUI);
    }

    /**
     * 创建并显示 GUI
     */
    private void createAndShowGUI() {
        // 创建窗体
        Frame frame = new Frame();

        // 设置窗体位置和尺寸
        frame.setBounds(500, 500, 500, 300);

        // 注意是awt包下的List，别导错了
        List list = new List();
        list.add("AAAAA");
        list.add("BBBBB");
        list.add("CCCCC");
        list.add("DDDDD");
        list.add("EEEEE");
        // 是否开启多选模式
        list.setMultipleMode(true);

        // 添加项监听器
        list.addItemListener(System.out::println);

        // 将List组件添加到窗体中
        frame.add(list);

        // 设置窗体可见
        frame.setVisible(true);
    }
}
