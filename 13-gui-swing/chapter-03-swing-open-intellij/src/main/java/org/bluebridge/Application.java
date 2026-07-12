package org.bluebridge;

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * 模仿IntelliJ IDEA界面布局的简单实现 仅实现布局结构，无具体功能逻辑
 *
 * @author lingwh
 * @date 2026/2/6 10:24
 */
public class Application {

    // 核心窗口容器（组合而非继承）
    private final JFrame mainFrame;

    // 构造方法：初始化核心容器和界面
    public Application() {
        // 1. 初始化主窗口（组合方式创建JFrame）
        mainFrame = new JFrame("IntelliJ IDEA");
        mainFrame.setSize(1200, 800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(new BorderLayout()); // 主布局

        // 2. 分步组装界面组件（解耦各区域的创建逻辑）
        assembleMenuBar();
        assembleWorkspace();
        assembleStatusBar();
    }

    /**
     * 组装顶部菜单栏（独立方法，职责单一）
     */
    private void assembleMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        // 创建基础菜单
        JMenu[] menus = {
                new JMenu("File"), new JMenu("Edit"), new JMenu("View"), new JMenu("Run"), new JMenu("Help")
        };
        // 添加所有菜单到菜单栏
        for (JMenu menu : menus) {
            menuBar.add(menu);
        }
        // 为窗口设置菜单栏
        mainFrame.setJMenuBar(menuBar);
    }

    /**
     * 组装核心工作区（左侧项目树 + 中央编辑区 + 右侧工具窗口）
     */
    private void assembleWorkspace() {
        // 1. 创建左侧项目树组件
        JScrollPane projectTreePanel = createProjectTreePanel();
        // 2. 创建中央代码编辑区组件
        JPanel editorPanel = createEditorPanel();
        // 3. 创建右侧工具窗口组件
        JPanel toolWindowPanel = createToolWindowPanel();

        // 4. 组装拆分面板
        // 第一步：拆分编辑区和右侧工具窗口
        JSplitPane rightSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, editorPanel, toolWindowPanel);
        rightSplit.setDividerLocation(800);
        rightSplit.setOneTouchExpandable(true);

        // 第二步：拆分左侧项目树和（编辑区+工具窗口）
        JSplitPane mainSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, projectTreePanel, rightSplit);
        mainSplit.setDividerLocation(250);
        mainSplit.setOneTouchExpandable(true);

        // 将核心工作区添加到窗口中央
        mainFrame.add(mainSplit, BorderLayout.CENTER);
    }

    /**
     * 创建左侧项目树面板（独立方法，便于复用/修改）
     */
    private JScrollPane createProjectTreePanel() {
        // 构建项目树节点
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("My Project");
        DefaultMutableTreeNode srcNode = new DefaultMutableTreeNode("src");
        DefaultMutableTreeNode mainJavaNode = new DefaultMutableTreeNode("main/java");
        mainJavaNode.add(new DefaultMutableTreeNode("com/example/IDE.java"));
        srcNode.add(mainJavaNode);
        srcNode.add(new DefaultMutableTreeNode("main/resources"));
        srcNode.add(new DefaultMutableTreeNode("test"));
        root.add(srcNode);
        root.add(new DefaultMutableTreeNode("lib"));
        root.add(new DefaultMutableTreeNode("out"));

        // 创建树组件并包装滚动面板
        JTree projectTree = new JTree(root);
        projectTree.setRootVisible(true);
        JScrollPane scrollPane = new JScrollPane(projectTree);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Project"));
        return scrollPane;
    }

    /**
     * 创建中央代码编辑区面板
     */
    private JPanel createEditorPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Code Editor"));

        // 模拟代码编辑区
        JTextArea codeEditor = new JTextArea();
        codeEditor.setText("// Welcome to Simple IDE (Composition Style)\n" +
                "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hello IDE!\");\n" +
                "    }\n}");
        codeEditor.setFont(new Font("Monospaced", Font.PLAIN, 14));
        panel.add(new JScrollPane(codeEditor), BorderLayout.CENTER);
        return panel;
    }

    /**
     * 创建右侧工具窗口面板
     */
    private JPanel createToolWindowPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Tool Windows"));

        JTextArea toolText = new JTextArea("Terminal / Problems / Structure / Database ...");
        toolText.setEditable(false);
        toolText.setBackground(Color.LIGHT_GRAY);
        panel.add(new JScrollPane(toolText), BorderLayout.CENTER);
        return panel;
    }

    /**
     * 组装底部状态栏
     */
    private void assembleStatusBar() {
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusBar.setBackground(Color.WHITE);
        statusBar.setBorder(BorderFactory.createEtchedBorder());

        // 状态栏元素
        JLabel[] statusLabels = {
                new JLabel(" Line: 1, Column: 1 "),
                new JLabel(" UTF-8 "),
                new JLabel(" Main Branch "),
                new JLabel(" No errors "),
                new JLabel(" 12:00 PM ")
        };

        // 添加元素（中间用水平胶水右对齐最后一个元素）
        for (int i = 0; i < statusLabels.length; i++) {
            statusBar.add(statusLabels[i]);
            if (i == statusLabels.length - 2) {
                statusBar.add(Box.createHorizontalGlue());
            }
        }

        // 添加到窗口底部
        mainFrame.add(statusBar, BorderLayout.SOUTH);
    }

    /**
     * 显示窗口（对外提供的统一入口）
     */
    public void createAndShowGUI() {
        mainFrame.setVisible(true);
    }

    // 程序入口
    public static void main(String[] args) {
        // Swing组件需在事件调度线程中运行
        SwingUtilities.invokeLater(() -> {
            Application application = new Application();
            application.createAndShowGUI();
        });
    }
}
