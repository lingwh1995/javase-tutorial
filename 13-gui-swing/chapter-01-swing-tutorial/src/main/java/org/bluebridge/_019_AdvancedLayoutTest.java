package org.bluebridge;

import java.awt.*;
import javax.swing.*;

/**
 * @author lingwh
 * @desc 高级布局管理器演示程序 演示多种常用的布局管理器及其特点，包含更多教学示例
 * @date 2026/7/9 00:00
 */
public class _019_AdvancedLayoutTest extends JFrame {
    
    public _019_AdvancedLayoutTest() {
        initializeFrame();
        setupMainPanel();
    }
    
    /**
     * 初始化主窗口
     */
    private void initializeFrame() {
        setTitle("Java Swing 高级布局管理器演示");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null); // 居中显示
    }
    
    /**
     * 设置主面板
     */
    private void setupMainPanel() {
        // 使用BorderLayout作为主布局
        setLayout(new BorderLayout());
        
        // 创建顶部说明面板
        JPanel topPanel = createTopPanel();
        add(topPanel, BorderLayout.NORTH);
        
        // 创建中央演示面板
        JPanel centerPanel = createCenterPanel();
        add(centerPanel, BorderLayout.CENTER);
        
        // 创建底部信息面板
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    /**
     * 创建顶部说明面板
     */
    private JPanel createTopPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("高级布局管理器演示"));
        panel.setPreferredSize(new Dimension(900, 70));
        
        JLabel titleLabel = new JLabel("Java Swing 高级布局管理器演示程序", JLabel.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        panel.add(titleLabel);
        
        return panel;
    }
    
    /**
     * 创建中央演示面板
     */
    private JPanel createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // 创建选项卡面板来展示不同布局
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // 添加各种布局演示面板
        tabbedPane.addTab("FlowLayout", createFlowLayoutPanel());
        tabbedPane.addTab("BorderLayout", createBorderLayoutPanel());
        tabbedPane.addTab("GridLayout", createGridLayoutPanel());
        tabbedPane.addTab("GridBagLayout", createGridBagLayoutPanel());
        tabbedPane.addTab("BoxLayout", createBoxLayoutPanel());
        tabbedPane.addTab("组合布局示例", createCombinedLayoutExample());
        tabbedPane.addTab("布局对比", createLayoutComparison());
        
        panel.add(tabbedPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * 创建底部信息面板
     */
    private JPanel createBottomPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(900, 40));
        
        JLabel infoLabel = new JLabel("提示：调整窗口大小以查看不同布局的效果 | 双击标签可查看布局代码示例", JLabel.CENTER);
        infoLabel.setForeground(Color.GRAY);
        panel.add(infoLabel);
        
        return panel;
    }
    
    /**
     * 创建FlowLayout演示面板 - 增强版
     */
    private JPanel createFlowLayoutPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createTitledBorder("FlowLayout - 流式布局"));
        
        // 添加不同大小的组件来演示FlowLayout的特性
        for (int i = 1; i <= 6; i++) {
            JButton button = new JButton("按钮 " + i);
            // 让某些按钮更大，以展示FlowLayout的灵活性
            if (i % 2 == 0) {
                button.setPreferredSize(new Dimension(120, 50));
            } else {
                button.setPreferredSize(new Dimension(80, 40));
            }
            panel.add(button);
        }
        
        // 添加标签和文本框
        JLabel label = new JLabel("输入框：");
        JTextField textField = new JTextField(15);
        panel.add(label);
        panel.add(textField);
        
        // 添加复选框
        JCheckBox checkBox = new JCheckBox("选择项");
        panel.add(checkBox);
        
        // 添加说明
        JLabel description = new JLabel("特点：按添加顺序从左到右排列，空间不足时自动换行");
        description.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        description.setForeground(Color.BLUE);
        panel.add(Box.createVerticalStrut(10)); // 添加一些垂直间距
        panel.add(description);
        
        // 添加布局原理说明
        JLabel principle = new JLabel("原理：组件按顺序放置，对齐方式可设置(LEFT, RIGHT, CENTER)");
        principle.setFont(new Font("微软雅黑", Font.ITALIC, 11));
        principle.setForeground(Color.DARK_GRAY);
        panel.add(principle);
        
        return panel;
    }
    
    /**
     * 创建BorderLayout演示面板 - 增强版
     */
    private JPanel createBorderLayoutPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("BorderLayout - 边界布局"));
        
        // 添加中央组件 - 使用滚动面板展示大量内容
        JTextArea centerText = new JTextArea(
            "BorderLayout.CENTER\n" +
            "这是中央区域，占据剩余空间\n" +
            "中央区域通常用来放置主要内容\n" +
            "可以是文本区域、表格或其他组件\n" +
            "当其他区域有组件时，中央区域会自动调整大小\n" +
            "这是中央区域的更多内容..."
        );
        centerText.setEditable(false);
        centerText.setLineWrap(true);
        centerText.setWrapStyleWord(true);
        centerText.setBackground(Color.LIGHT_GRAY);
        JScrollPane scrollPane = new JScrollPane(centerText);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // 添加北部组件
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton northButton = new JButton("North (顶部)");
        northButton.setPreferredSize(new Dimension(150, 30));
        northPanel.add(northButton);
        panel.add(northPanel, BorderLayout.NORTH);
        
        // 添加南部组件
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton southButton = new JButton("South (底部)");
        southButton.setPreferredSize(new Dimension(150, 30));
        southPanel.add(southButton);
        panel.add(southPanel, BorderLayout.SOUTH);
        
        // 添加西部组件
        JPanel westPanel = new JPanel(new BorderLayout());
        JButton westButton = new JButton("West\n(左侧)");
        westButton.setPreferredSize(new Dimension(100, 100));
        westPanel.add(westButton, BorderLayout.CENTER);
        westPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        panel.add(westPanel, BorderLayout.WEST);
        
        // 添加东部组件
        JPanel eastPanel = new JPanel(new BorderLayout());
        JButton eastButton = new JButton("East\n(右侧)");
        eastButton.setPreferredSize(new Dimension(100, 100));
        eastPanel.add(eastButton, BorderLayout.CENTER);
        eastPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        panel.add(eastPanel, BorderLayout.EAST);
        
        // 添加说明
        JLabel description = new JLabel("特点：分为北、南、东、西、中五个区域，每个区域只能放置一个组件");
        description.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        description.setForeground(Color.BLUE);
        panel.add(description, BorderLayout.SOUTH);
        
        return panel;
    }
    
    /**
     * 创建GridLayout演示面板 - 增强版
     */
    private JPanel createGridLayoutPanel() {
        // 使用3行3列的网格布局，展示更复杂的网格
        JPanel panel = new JPanel(new GridLayout(3, 3, 8, 8)); // 行数，列数，水平间距，垂直间距
        panel.setBorder(BorderFactory.createTitledBorder("GridLayout - 网格布局"));
        
        // 添加9个不同内容的按钮形成3x3的网格
        String[] labels = {
            "登录", "注册", "退出", 
            "保存", "取消", "重置",
            "查询", "导出", "打印"
        };
        
        for (int i = 0; i < 9; i++) {
            JButton button = new JButton(labels[i]);
            button.setPreferredSize(new Dimension(100, 50));
            
            // 根据位置设置不同颜色以更好地区分
            switch(i) {
                case 0: button.setBackground(Color.CYAN); break;
                case 1: button.setBackground(Color.YELLOW); break;
                case 2: button.setBackground(Color.PINK); break;
                default: button.setBackground(null); // 默认颜色
            }
            
            panel.add(button);
        }
        
        // 添加说明
        JLabel description = new JLabel("特点：将容器划分为相等大小的矩形网格，组件平均分配空间");
        description.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        description.setForeground(Color.BLUE);
        panel.add(description);
        
        return panel;
    }
    
    /**
     * 创建GridBagLayout演示面板 - 增强版
     */
    private JPanel createGridBagLayoutPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("GridBagLayout - 网格包布局"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        // 添加标题标签
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        JLabel titleLabel = new JLabel("GridBagLayout 演示 - 复杂表单布局", JLabel.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));
        titleLabel.setForeground(Color.BLUE);
        panel.add(titleLabel, gbc);
        
        // 添加姓名标签
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel nameLabel = new JLabel("姓名:");
        panel.add(nameLabel, gbc);
        
        // 添加姓名输入框
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField nameField = new JTextField(15);
        panel.add(nameField, gbc);
        
        // 添加年龄标签
        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel ageLabel = new JLabel("年龄:");
        panel.add(ageLabel, gbc);
        
        // 添加年龄输入框
        gbc.gridx = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField ageField = new JTextField(5);
        panel.add(ageField, gbc);
        
        // 添加地址标签（跨两列）
        gbc.gridx = 4;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel addressLabel = new JLabel("地址:");
        panel.add(addressLabel, gbc);
        
        // 添加地址输入框（跨两列）
        gbc.gridx = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 2.0;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField addressField = new JTextField(25);
        panel.add(addressField, gbc);
        
        // 添加备注多行文本区域
        gbc.gridx = 6;
        gbc.gridwidth = 6;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(10, 5, 5, 5);
        JTextArea remarksArea = new JTextArea(3, 30);
        remarksArea.setBorder(BorderFactory.createLoweredBevelBorder());
        JScrollPane remarksScroll = new JScrollPane(remarksArea);
        panel.add(remarksScroll, gbc);
        
        // 添加按钮面板
        gbc.gridx = 7;
        gbc.gridwidth = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("保存");
        JButton cancelButton = new JButton("取消");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        panel.add(buttonPanel, gbc);
        
        // 添加说明
        JLabel description = new JLabel("特点：最灵活的布局，可设置组件位置、大小、拉伸比例等");
        description.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        description.setForeground(Color.BLUE);
        gbc.gridx = 8;
        gbc.gridwidth = 6;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 0, 0);
        panel.add(description, gbc);
        
        return panel;
    }
    
    /**
     * 创建BoxLayout演示面板 - 增强版
     */
    private JPanel createBoxLayoutPanel() {
        // 使用垂直Box布局作为主容器
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("BoxLayout - 盒式布局"));
        
        // 创建工具栏样式的水平布局
        JPanel toolbarPanel = new JPanel();
        toolbarPanel.setLayout(new BoxLayout(toolbarPanel, BoxLayout.X_AXIS));
        toolbarPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        toolbarPanel.setBorder(BorderFactory.createEtchedBorder());
        
        toolbarPanel.add(new JLabel("工具栏: "));
        toolbarPanel.add(Box.createHorizontalStrut(10));
        toolbarPanel.add(new JButton("新建"));
        toolbarPanel.add(Box.createHorizontalStrut(5));
        toolbarPanel.add(new JButton("打开"));
        toolbarPanel.add(Box.createHorizontalStrut(5));
        toolbarPanel.add(new JButton("保存"));
        toolbarPanel.add(Box.createHorizontalGlue());
        toolbarPanel.add(new JButton("帮助"));
        
        panel.add(toolbarPanel);
        panel.add(Box.createVerticalStrut(10));
        
        // 创建表单样式的垂直布局
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // 用户名行
        JPanel usernameRow = new JPanel();
        usernameRow.setLayout(new BoxLayout(usernameRow, BoxLayout.X_AXIS));
        usernameRow.add(new JLabel("用户名:"));
        usernameRow.add(Box.createHorizontalStrut(5));
        usernameRow.add(new JTextField(15));
        usernameRow.add(Box.createHorizontalGlue());
        formPanel.add(usernameRow);
        formPanel.add(Box.createVerticalStrut(5));
        
        // 密码行
        JPanel passwordRow = new JPanel();
        passwordRow.setLayout(new BoxLayout(passwordRow, BoxLayout.X_AXIS));
        passwordRow.add(new JLabel("密码:"));
        passwordRow.add(Box.createHorizontalStrut(5));
        passwordRow.add(new JPasswordField(15));
        passwordRow.add(Box.createHorizontalGlue());
        formPanel.add(passwordRow);
        formPanel.add(Box.createVerticalStrut(5));
        
        // 按钮行
        JPanel buttonRow = new JPanel();
        buttonRow.setLayout(new BoxLayout(buttonRow, BoxLayout.X_AXIS));
        buttonRow.add(Box.createHorizontalGlue());
        buttonRow.add(new JButton("登录"));
        buttonRow.add(Box.createHorizontalStrut(5));
        buttonRow.add(new JButton("注册"));
        buttonRow.add(Box.createHorizontalStrut(5));
        buttonRow.add(new JButton("取消"));
        formPanel.add(buttonRow);
        
        panel.add(formPanel);
        panel.add(Box.createVerticalStrut(15));
        
        // 创建复杂嵌套布局示例
        JPanel complexPanel = new JPanel();
        complexPanel.setLayout(new BoxLayout(complexPanel, BoxLayout.X_AXIS));
        complexPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        complexPanel.setBorder(BorderFactory.createTitledBorder("复杂嵌套示例"));
        
        // 左侧垂直布局
        Box leftBox = Box.createVerticalBox();
        leftBox.add(new JLabel("左侧菜单"));
        leftBox.add(Box.createVerticalStrut(5));
        leftBox.add(new JButton("菜单项1"));
        leftBox.add(Box.createVerticalStrut(3));
        leftBox.add(new JButton("菜单项2"));
        leftBox.add(Box.createVerticalStrut(3));
        leftBox.add(new JButton("菜单项3"));
        leftBox.add(Box.createVerticalGlue());
        complexPanel.add(leftBox);
        
        complexPanel.add(Box.createHorizontalStrut(10));
        
        // 右侧垂直布局
        Box rightBox = Box.createVerticalBox();
        rightBox.add(new JLabel("主内容区域"));
        rightBox.add(Box.createVerticalStrut(5));
        
        // 内部水平布局
        Box innerHBox = Box.createHorizontalBox();
        innerHBox.add(new JLabel("搜索:"));
        innerHBox.add(Box.createHorizontalStrut(5));
        innerHBox.add(new JTextField(10));
        innerHBox.add(Box.createHorizontalStrut(5));
        innerHBox.add(new JButton("查找"));
        rightBox.add(innerHBox);
        
        rightBox.add(Box.createVerticalStrut(10));
        rightBox.add(new JScrollPane(new JList<>(new String[]{"项目1", "项目2", "项目3", "项目4"})));
        rightBox.add(Box.createVerticalGlue());
        complexPanel.add(rightBox);
        
        panel.add(complexPanel);
        
        // 添加说明
        JLabel description = new JLabel("特点：可在X轴或Y轴方向排列组件，支持弹性间隔");
        description.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        description.setForeground(Color.BLUE);
        panel.add(description);
        
        return panel;
    }
    
    /**
     * 创建组合布局示例 - 演示如何组合多种布局
     */
    private JPanel createCombinedLayoutExample() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("组合布局示例 - 模拟真实应用界面"));
        
        // 顶部工具栏 - 使用BorderLayout.NORTH
        JPanel topToolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topToolbar.add(new JButton("文件"));
        topToolbar.add(new JButton("编辑"));
        topToolbar.add(new JButton("视图"));
        topToolbar.add(new JButton("帮助"));
        panel.add(topToolbar, BorderLayout.NORTH);
        
        // 左侧导航面板 - 使用BorderLayout.WEST
        JPanel leftNav = new JPanel(new BorderLayout());
        leftNav.setBorder(BorderFactory.createTitledBorder("导航"));
        DefaultListModel<String> navModel = new DefaultListModel<>();
        navModel.addElement("主页");
        navModel.addElement("产品");
        navModel.addElement("订单");
        navModel.addElement("客户");
        navModel.addElement("报表");
        JList<String> navList = new JList<>(navModel);
        leftNav.add(new JScrollPane(navList), BorderLayout.CENTER);
        leftNav.setPreferredSize(new Dimension(150, 100));
        panel.add(leftNav, BorderLayout.WEST);
        
        // 中央内容区域 - 使用GridBagLayout进行复杂布局
        JPanel centerContent = new JPanel(new GridBagLayout());
        centerContent.setBorder(BorderFactory.createTitledBorder("主内容"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        // 添加搜索栏
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("搜索:"));
        searchPanel.add(new JTextField(15));
        searchPanel.add(new JButton("搜索"));
        centerContent.add(searchPanel, gbc);
        
        // 添加表格
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 5, 5, 5);
        
        String[] columnNames = {"ID", "名称", "价格", "库存"};
        Object[][] data = {
            {"1", "笔记本电脑", "5999", "10"},
            {"2", "鼠标", "99", "100"},
            {"3", "键盘", "299", "50"},
            {"4", "显示器", "1999", "20"}
        };
        
        JTable table = new JTable(data, columnNames);
        JScrollPane tableScrollPane = new JScrollPane(table);
        centerContent.add(tableScrollPane, gbc);
        
        // 添加底部按钮
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(5, 5, 5, 5);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(new JButton("新增"));
        buttonPanel.add(new JButton("编辑"));
        buttonPanel.add(new JButton("删除"));
        centerContent.add(buttonPanel, gbc);
        
        panel.add(centerContent, BorderLayout.CENTER);
        
        // 底部状态栏 - 使用BorderLayout.SOUTH
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
        statusBar.add(new JLabel("就绪"));
        statusBar.add(Box.createHorizontalGlue());
        statusBar.add(new JLabel("2026-02-05"));
        panel.add(statusBar, BorderLayout.SOUTH);
        
        return panel;
    }
    
    /**
     * 创建布局对比面板 - 并排显示不同布局的效果
     */
    private JPanel createLayoutComparison() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("布局对比 - 相同组件的不同布局效果"));
        
        // 创建包含相同组件的多个面板，分别使用不同布局
        JPanel comparisonPanels = new JPanel(new GridLayout(2, 2, 10, 10));
        
        // FlowLayout对比面板
        JPanel flowPanel = new JPanel(new FlowLayout());
        flowPanel.setBorder(BorderFactory.createTitledBorder("FlowLayout"));
        addCommonComponents(flowPanel);
        
        // BorderLayout对比面板
        JPanel borderPanel = new JPanel(new BorderLayout());
        borderPanel.setBorder(BorderFactory.createTitledBorder("BorderLayout"));
        addCommonComponents(borderPanel);
        
        // GridLayout对比面板
        JPanel gridPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        gridPanel.setBorder(BorderFactory.createTitledBorder("GridLayout"));
        addCommonComponents(gridPanel);
        
        // BoxLayout对比面板
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
        boxPanel.setBorder(BorderFactory.createTitledBorder("BoxLayout"));
        addCommonComponents(boxPanel);
        
        comparisonPanels.add(flowPanel);
        comparisonPanels.add(borderPanel);
        comparisonPanels.add(gridPanel);
        comparisonPanels.add(boxPanel);
        
        panel.add(comparisonPanels, BorderLayout.CENTER);
        
        // 添加说明文本
        JTextArea explanation = new JTextArea(
            "对比说明：\n" +
            "1. FlowLayout: 组件按顺序排列，空间不足时换行\n" +
            "2. BorderLayout: 组件分布在五个区域\n" +
            "3. GridLayout: 组件平均分配网格空间\n" +
            "4. BoxLayout: 组件垂直堆叠\n" +
            "\n调整窗口大小可观察各布局的响应行为差异。"
        );
        explanation.setEditable(false);
        explanation.setOpaque(false);
        explanation.setFont(new Font("微软雅黑", Font.PLAIN, 11));
        panel.add(explanation, BorderLayout.SOUTH);
        
        return panel;
    }
    
    /**
     * 为对比面板添加相同的组件
     */
    private void addCommonComponents(Container container) {
        // 检查容器类型并适当地添加组件
        if (container.getClass().getSimpleName().equals("JPanel")) {
            JPanel panel = (JPanel) container;
            if (panel.getLayout() instanceof BorderLayout) {
                // 对于BorderLayout，只在中心区域添加组件
                JPanel centerPanel = new JPanel(new FlowLayout());
                centerPanel.add(new JButton("按钮1"));
                centerPanel.add(new JTextField("文本1", 8));
                centerPanel.add(new JLabel("标签1"));
                centerPanel.add(new JButton("按钮2"));
                centerPanel.add(new JTextField("文本2", 8));
                panel.add(centerPanel, BorderLayout.CENTER);
            } else {
                // 对于其他布局，直接添加组件
                container.add(new JButton("按钮1"));
                container.add(new JTextField("文本1", 8));
                container.add(new JLabel("标签1"));
                container.add(new JButton("按钮2"));
                container.add(new JTextField("文本2", 8));
                container.add(new JLabel("标签2"));
            }
        } else {
            // 对于其他类型的容器，直接添加组件
            container.add(new JButton("按钮1"));
            container.add(new JTextField("文本1", 8));
            container.add(new JLabel("标签1"));
            container.add(new JButton("按钮2"));
            container.add(new JTextField("文本2", 8));
            container.add(new JLabel("标签2"));
        }
    }
    
    public static void main(String[] args) {
        // 设置系统外观
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 在事件调度线程中创建和显示GUI
        SwingUtilities.invokeLater(() -> {
            try {
                new _019_AdvancedLayoutTest().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
  }
}
