package org.bluebridge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author lingwh
 * @desc 常用组件 - 标签、文本框、单选框、复选框、按钮、文本区域
 * @date 2025/8/4 18:11
 */
public class Lesson_04_ComponentsTest {

    public static void main(String[] args) {
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - lambda 表达式调用
        // SwingUtilities.invokeLater(() -> new _004_ComponentsTest().createAndShowGUI());

        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - 方法引用调用
        SwingUtilities.invokeLater(new Lesson_04_ComponentsTest()::createAndShowGUI);
    }

    /**
     * 创建并显示 GUI
     */
    private void createAndShowGUI() {
        // 创建窗体
        Frame frame = new Frame();
        // 设置窗体位置和尺寸
        frame.setBounds(500, 500, 500, 300);
        // 设置窗体标题
        frame.setTitle("Components");
        // 设置窗窗体字体（窗体中所有元素如果没有单独设置都会继承这个字体）
        frame.setFont(new Font("Monospaced", Font.BOLD, 16));
        // 设置窗体布局
        frame.setLayout(null);

        /**
         * 标签组件
         */
        // 创建标签
        Label label = new Label();
        // 设置标签文本
        label.setText("USERNAME");
        // 设置标签位置和尺寸
        label.setBounds(20, 30, 100, 30);
        // 设置标签字体
        label.setFont(new Font("Monospaced", Font.BOLD, 16));
        // 设置标签背景色
        label.setBackground(Color.ORANGE);
        // 设置标签前景色
        label.setForeground(Color.YELLOW);
        // 设置标签事件
        frame.add(label);

        // 打印所有可用字体
        /*
        Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
        Arrays.stream(fonts).forEach(font -> {
            System.out.println(font.getName());
        });*/

        /**
         * 文本框组件
         */
        // 创建文本框
        TextField textField = new TextField();
        // 设置文本框位置和尺寸
        textField.setBounds(120, 30, 100, 30);
        // 给文本框绑定事件
        textField.addActionListener(e -> {
            System.out.println("文本框内容：" + textField.getText());
        });
        // 设置文本框内容
        //textField.setText("我是文本框");
        textField.setEchoChar('*');
        frame.add(textField);

        /**
         * 单选框组
         */
        //创建勾选框组
        CheckboxGroup group = new CheckboxGroup();
        Checkbox c1 = new Checkbox("basketball");
        c1.setBounds(20, 60, 120, 30);
        frame.add(c1);

        Checkbox c2 = new Checkbox("baseball");
        c2.setBounds(150, 60, 120, 30);
        frame.add(c2);
        //多个勾选框都可以添加到勾选框组中
        c1.setCheckboxGroup(group);
        c2.setCheckboxGroup(group);


        // 添加选择状态变化事件监听器
        c1.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                System.out.println("选择了: " + c1.getLabel());
            }
        });

        c2.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                System.out.println("选择了: " + c2.getLabel());
            }
        });

        /**
         * 文本区域组件
         */
        // 创建文本区域
        TextArea textArea = new TextArea();
        // 设置文本区域位置和尺寸
        textArea.setBounds(20, 90, 300, 100);
        // 给文本区域绑定事件
        // 给文本区域绑定焦点事件
        textArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                System.out.println("文本区域获得焦点");
            }

            @Override
            public void focusLost(FocusEvent e) {
                System.out.println("文本区域失去焦点");
            }
        });
        // 给文本区域绑定键盘事件
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("按键按下：" + e.getKeyChar());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("按键释放：" + e.getKeyCode());
            }
        });
        frame.add(textArea);

        /**
         * 复选框组件
         */
        // 创建复选框
        Checkbox checkbox = new Checkbox("remember");
        // 设置复选框位置和尺寸
        checkbox.setBounds(130, 200, 100, 30);
        // 添加状态改变事件监听
        checkbox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                System.out.println("复选框被选中了！");
            } else {
                System.out.println("复选框被取消选中了！");
            }
        });
        frame.add(checkbox);

        /**
         * 按钮组件
         */
        // 创建按钮
        Button button = new Button("button");
        // 设置按钮位置和尺寸
        button.setBounds(20, 200, 100, 30);
        // 给按钮绑定事件
        button.addActionListener(e -> {
            System.out.println("按钮被点击了!");
            String text = textArea.getText();
            System.out.println("文本区域内容：" + text);
        });
        frame.add(button);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("收到关闭请求，正在退出程序......");
                // 释放窗体资源
                frame.dispose();
                // 退出虚拟机（如果是主程序）
                //System.exit(0);
            }
        });

        // 设置窗体可见
        frame.setVisible(true);
    }

}
