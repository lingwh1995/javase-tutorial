package org.bluebridge;

import javax.swing.*;
import java.awt.*;

/**
 * @author think
 * @desc 加载文件对话框
 * @date 2026/1/30 17:49
 */
public class Lesson_18_FileDialogTest {

    public static void main(String[] args) {
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - lambda 表达式调用
        // SwingUtilities.invokeLater(() -> new _018_FileDialogTest().createAndShowGUI());

        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - 方法引用调用
        SwingUtilities.invokeLater(new Lesson_18_FileDialogTest()::createAndShowGUI);
    }

    /**
     * 创建并显示 GUI
     */
    private void createAndShowGUI() {
        // 创建窗体
        Frame frame = new Frame();
        // 设置窗体位置和尺寸
        frame.setBounds(500, 500, 500, 300);
        // 禁用布局管理器，使用绝对定位
        frame.setLayout(null);

        // 选择文件对话框类型，可以是加载文件或是保存文件
        FileDialog dialog = new FileDialog(frame, "Please select a file", FileDialog.LOAD);

        // 添加一个按钮
        Button button = new Button("Select File");
        // 设置按钮位置和大小
        button.setBounds(100, 100, 100, 30);
        // 添加事件监听器
        button.addActionListener(e -> {
            dialog.setVisible(true);

            // 获取选择的文件信息
            String directory = dialog.getDirectory();  // 获取选择的目录路径
            String filename = dialog.getFile();        // 获取选择的文件名

            if (filename != null) {
                System.out.println("选择的目录: " + directory);
                System.out.println("选择的文件: " + filename);
                System.out.println("完整路径: " + directory + filename);
            } else {
                System.out.println("用户取消了文件选择");
            }
        });
        frame.add(button);

        // 设置窗体可见
        frame.setVisible(true);
    }

}
