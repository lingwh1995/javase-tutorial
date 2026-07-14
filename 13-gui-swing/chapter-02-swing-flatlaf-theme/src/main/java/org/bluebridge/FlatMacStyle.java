package org.bluebridge;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;
import java.awt.*;

/**
 * 使用 FlatLaf 打造现代 macOS 风格界面
 *
 * @author lingwh
 * @date 2026/1/22 15:02
 */
public class FlatMacStyle {

    public static void main(String[] args) {
        // 1. 设置外观为 FlatLaf 的 macOS 浅色主题
        try {
            // 亮色mac主题
            UIManager.setLookAndFeel(new FlatMacLightLaf());
            // 暗色mac主题
            // UIManager.setLookAndFeel(new FlatMacDarkLaf());
            // 可选：设置一些 macOS 特有的微调
            UIManager.put("Button.arc", 10); // 按钮圆角
            UIManager.put("Component.arc", 10); // 组件圆角
            UIManager.put("ProgressBar.arc", 10); // 进度条圆角
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("FlatLaf macOS Dashboard");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 550);

            // 主容器采用 BorderLayout
            JPanel root = new JPanel(new BorderLayout());

            // 2. 模拟 macOS 的顶部工具栏
            JToolBar toolBar = new JToolBar();
            toolBar.setFloatable(false);
            toolBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

            JButton addBtn = new JButton("新建");
            JButton deleteBtn = new JButton("删除");
            // 模仿 macOS 的搜索框样式
            JTextField searchField = new JTextField(15);
            // 优雅的占位提示文字
            searchField.putClientProperty("JTextField.placeholderText", "搜索文件...");
            // 自动在搜索框右侧添加一个小叉叉
            searchField.putClientProperty("JTextField.showClearButton", true);
            searchField.putClientProperty("JTextField.selectAllOnFocusPolicy", "always");

            toolBar.add(addBtn);
            toolBar.add(Box.createHorizontalStrut(10));
            toolBar.add(deleteBtn);
            toolBar.add(Box.createHorizontalGlue());
            toolBar.add(new JLabel("过滤: "));
            toolBar.add(searchField);

            // 3. 左侧导航栏 (树形结构或列表)
            String[] treeData = {"我的文件", "最近使用", "下载", "文稿", "图片"};
            JList<String> navList = new JList<>(treeData);
            navList.setFixedCellHeight(35);
            navList.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            // 重点：设置 macOS 侧边栏样式
            navList.putClientProperty("JList.selectionArc", 8);

            JScrollPane scrollNav = new JScrollPane(navList);
            scrollNav.setPreferredSize(new Dimension(160, 0));
            scrollNav.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.LIGHT_GRAY));

            // 4. 中央主要内容区域
            JPanel contentPanel = new JPanel(new GridBagLayout());
            contentPanel.setBackground(Color.WHITE);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(15, 15, 15, 15);

            // 添加一个进度条演示
            JProgressBar pb = new JProgressBar();
            pb.setValue(65);
            pb.setStringPainted(true);

            // 添加一个典型的 macOS 分段按钮模拟
            JPanel segmentPanel = new JPanel(new GridLayout(1, 3));
            segmentPanel.add(new JButton("日间"));
            segmentPanel.add(new JButton("周间"));
            segmentPanel.add(new JButton("月间"));

            gbc.gridx = 0; gbc.gridy = 0;
            contentPanel.add(new JLabel("系统同步进度:"), gbc);
            gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
            contentPanel.add(pb, gbc);

            gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
            contentPanel.add(new JLabel("统计视图:"), gbc);
            gbc.gridx = 1;
            contentPanel.add(segmentPanel, gbc);

            // 5. 组合界面
            root.add(toolBar, BorderLayout.NORTH);
            root.add(scrollNav, BorderLayout.WEST);
            root.add(contentPanel, BorderLayout.CENTER);

            frame.add(root);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
