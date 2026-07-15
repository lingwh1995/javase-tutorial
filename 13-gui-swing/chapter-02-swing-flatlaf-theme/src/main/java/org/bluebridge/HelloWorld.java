package org.bluebridge;

import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.*;
import javax.swing.*;

/**
 * FlatLaf深色主题示例
 *
 * @author lingwh
 * @date 2026/1/22 15:02
 */
public class HelloWorld {

    public static void main(String[] args) {
        // 设置深色主题（这是 FlatLaf 的精髓）
        FlatDarkLaf.setup();

        // 建议设置一下 UI 微调，比如圆角
        UIManager.put("Button.arc", 10);
        UIManager.put("Component.arc", 10);

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("现代 Swing 应用");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 400);
            frame.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

            frame.add(new JButton("主要按钮"));
            frame.add(new JCheckBox("勾选框"));
            frame.add(new JTextField("请输入内容...", 15));

            frame.setLocationRelativeTo(null); // 居中显示
            frame.setVisible(true);
        });
    }
}
