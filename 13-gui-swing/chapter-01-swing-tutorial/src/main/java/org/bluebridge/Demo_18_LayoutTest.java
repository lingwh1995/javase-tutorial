package org.bluebridge;

import java.awt.*;
import javax.swing.*;

/**
 * 布局管理器示例
 *
 * @author lingwh
 * @date 2025/8/4 18:24
 */
public class Demo_18_LayoutTest {

    public static void main(String[] args) {
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
        // SwingUtilities.invokeLater(() -> new SwingLayouts().createAndShowGUI());
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT)
        SwingUtilities.invokeLater(new Demo_18_LayoutTest()::createAndShowGUI);
    }

    /**
     * 创建并显示 GUI
     */
    private void createAndShowGUI() {
        // 创建 JFrame 实例
        JFrame frame = new JFrame("布局管理器示例");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // 使用选项卡展示不同布局
        JTabbedPane tabbedPane = new JTabbedPane();

        // BorderLayout示例
        JPanel borderPanel = new JPanel(new BorderLayout());
        borderPanel.add(new JButton("北"), BorderLayout.NORTH);
        borderPanel.add(new JButton("南"), BorderLayout.SOUTH);
        borderPanel.add(new JButton("西"), BorderLayout.WEST);
        borderPanel.add(new JButton("东"), BorderLayout.EAST);
        borderPanel.add(new JButton("中"), BorderLayout.CENTER);
        tabbedPane.addTab("BorderLayout", borderPanel);

        // FlowLayout示例
        JPanel flowPanel = new JPanel(new FlowLayout());
        for (int i = 1; i <= 10; i++) {
            flowPanel.add(new JButton("按钮" + i));
        }
        tabbedPane.addTab("FlowLayout", flowPanel);

        // GridLayout示例
        JPanel gridPanel = new JPanel(new GridLayout(3, 3));
        for (int i = 1; i <= 8; i++) {
            gridPanel.add(new JButton("格子" + i));
        }
        tabbedPane.addTab("GridLayout", gridPanel);

        // 使用垂直Box布局作为主容器
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
        boxPanel.setBorder(BorderFactory.createTitledBorder("BoxLayout - 盒式布局"));

        // 创建水平布局的第一个子面板
        JPanel horizontalPanel1 = new JPanel();
        horizontalPanel1.setLayout(new BoxLayout(horizontalPanel1, BoxLayout.X_AXIS));
        // 左对齐
        horizontalPanel1.setAlignmentX(Component.LEFT_ALIGNMENT);

        // 向第一个水平面板添加组件
        horizontalPanel1.add(new JButton("水平1"));
        // 固定间隔
        horizontalPanel1.add(Box.createRigidArea(new Dimension(10, 0)));
        horizontalPanel1.add(new JButton("水平2"));
        // 弹性间隔
        horizontalPanel1.add(Box.createHorizontalGlue());
        horizontalPanel1.add(new JButton("水平3"));

        // 将第一个水平面板添加到主面板
        boxPanel.add(horizontalPanel1);

        // 添加垂直间隔
        boxPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // 创建水平布局的第二个子面板
        JPanel horizontalPanel2 = new JPanel();
        horizontalPanel2.setLayout(new BoxLayout(horizontalPanel2, BoxLayout.X_AXIS));
        horizontalPanel2.setAlignmentX(Component.LEFT_ALIGNMENT);

        // 添加更多组件
        horizontalPanel2.add(new JLabel("标签:"));
        horizontalPanel2.add(Box.createRigidArea(new Dimension(5, 0)));
        horizontalPanel2.add(new JTextField(10));
        horizontalPanel2.add(Box.createRigidArea(new Dimension(10, 0)));
        horizontalPanel2.add(new JButton("提交"));

        // 将第二个水平面板添加到主面板
        boxPanel.add(horizontalPanel2);
        // 添加垂直间隔
        boxPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // 添加一个垂直的Box容器
        Box verticalBox = Box.createVerticalBox();
        verticalBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        verticalBox.add(new JLabel("垂直盒布局示例:"));
        verticalBox.add(Box.createRigidArea(new Dimension(0, 5)));
        verticalBox.add(new JButton("垂直按钮1"));
        verticalBox.add(Box.createRigidArea(new Dimension(0, 5)));
        verticalBox.add(new JButton("垂直按钮2"));
        verticalBox.add(Box.createRigidArea(new Dimension(0, 5)));
        verticalBox.add(new JButton("垂直按钮3"));

        boxPanel.add(verticalBox);
        tabbedPane.addTab("BoxLayout", boxPanel);

        // 添加选项卡到窗口
        frame.add(tabbedPane);

        // 显示窗口
        frame.setVisible(true);
    }
}
