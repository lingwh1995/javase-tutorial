package org.bluebridge;

import java.awt.*;
import java.awt.event.ItemEvent;
import javax.swing.*;

/**
 * 菜单栏和菜单栏的子菜单
 *
 * @author lingwh
 * @date 2026/1/29 22:30
 */
public class Demo_15_MenuBarAndMenuTest {

    public static void main(String[] args) {
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - lambda 表达式调用
        // SwingUtilities.invokeLater(() -> new _015_MenuBarAndMenuTest().createAndShowGUI());

        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - 方法引用调用
        SwingUtilities.invokeLater(new Demo_15_MenuBarAndMenuTest()::createAndShowGUI);
    }

    /**
     * 创建并显示 GUI
     */
    private void createAndShowGUI() {
        // 创建窗体
        Frame frame = new Frame();
        // 设置窗体位置和尺寸
        frame.setBounds(500, 500, 500, 300);
        // 创建菜单栏
        MenuBar bar = new MenuBar();
        Menu menu = new Menu("File");

        /**
         * 第一个子菜单栏 - 添加了点击事件
         */
        MenuItem openItem = new MenuItem("Open");
        // 添加点击事件
        openItem.addActionListener(e -> System.out.println("打开菜单被点击了......"));
        menu.add(openItem);

        /**
         * 第二个子菜单栏 - 添加了快捷键和点击事件
         */
        MenuItem saveItem = new MenuItem("Save");
        // MenuShortcut就是指定快捷键组合，默认情况下是Ctrl+指定按键
        saveItem.setShortcut(new MenuShortcut('S'));
        // 第二个参数指定为true表示需要Ctrl+Shift+指定按键
        // saveItem.setShortcut(new MenuShortcut('S', true));
        saveItem.addActionListener(e -> System.out.println("保存菜单被点击了或用户按下了 快捷键 Ctrl + S ......"));
        menu.add(saveItem);

        /**
         * 第三个子菜单栏 - 添加了复选框和点击事件 对于 CheckboxMenuItem，应使用 ItemListener 来监听选中状态变化，而不是 ActionListener。这是
         * AWT 框架的设计特性
         */
        CheckboxMenuItem checkButton = new CheckboxMenuItem("Check Button");
        checkButton.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                System.out.println("复选菜单被选中了......");
            } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                System.out.println("复选菜单被取消选中了......");
            }
        });
        // 添加选中状态变化监听器状态事件，下面这行代码没有效果，原因看上面
        // checkButton.addActionListener(e -> System.out.println("复选菜单被点击了......"));
        menu.add(checkButton);

        // 把菜单添加到菜单栏
        bar.add(menu);

        // 为窗口设定刚刚定义好的菜单栏
        frame.setMenuBar(bar);

        // 设置窗体可见
        frame.setVisible(true);
    }
}
