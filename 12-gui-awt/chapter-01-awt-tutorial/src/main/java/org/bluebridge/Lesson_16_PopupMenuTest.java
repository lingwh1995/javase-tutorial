package org.bluebridge;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * @author lingwh
 * @desc 弹出菜单
 * @date 2026/1/29 22:30
 */
public class Lesson_16_PopupMenuTest {

    public static void main(String[] args) {
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - lambda 表达式调用
        // SwingUtilities.invokeLater(() -> new _016_PopupMenuTest().createAndShowGUI());

        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - 方法引用调用
        SwingUtilities.invokeLater(new Lesson_16_PopupMenuTest()::createAndShowGUI);
    }

    /**
     * 创建并显示 GUI
     */
    private void createAndShowGUI() {
        // 创建窗体
        Frame frame = new Frame();
        // 设置窗体位置和尺寸
        frame.setBounds(500, 500, 500, 300);
        // 创建弹出菜单
        PopupMenu menu = new PopupMenu();
        // 每一个选项依然是使用MenuItem
        menu.add(new MenuItem("Option1"));
        menu.add(new MenuItem("Option2"));
        // 注意，弹出菜单也要作为组件加入到窗口中（但是默认情况下不显示）
        frame.add(menu);

        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // 监听鼠标右键
                if (e.getButton() == MouseEvent.BUTTON3) {
                    //要展示弹出菜单，我们只需要调用show方法即可
                    menu.show(frame, e.getX(), e.getY());
                    // 注意，第一个参数必须是弹出菜单所加入的窗口或是窗口中的任意一个组件
                    // 后面的坐标就是相对于这个窗口或是组件的原点（左上角）这个位置进行弹出
                    // 我们这里写的就是相对于当前窗口的左上角，鼠标点击位置的x、y位置弹出窗口
                }
            }
        });
        // 设置窗体可见
        frame.setVisible(true);
    }
}
