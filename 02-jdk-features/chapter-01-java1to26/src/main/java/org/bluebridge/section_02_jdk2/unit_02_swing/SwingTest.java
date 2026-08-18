package org.bluebridge.section_02_jdk2.unit_02_swing;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;

/**
 * JDK 1.2 Swing GUI 组件特性测试
 *
 * JDK 1.2 引入了 Swing 图形用户界面(GUI)工具包, 它提供了比 AWT 更丰富、更轻量级的组件:
 * 1. Swing 组件是纯 Java 实现的(轻量级), 不依赖本地平台的窗口系统
 * 2. Swing 提供了丰富的组件集: JFrame、JButton、JLabel、JTextField、JTextArea 等
 * 3. Swing 支持可插拔的外观(Pluggable Look and Feel), 可以在不同平台保持统一外观
 * 4. Swing 采用 MVC(Model-View-Controller)架构, 组件模型和视图分离
 * 5. Swing 提供了更多高级组件: JTable、JTree、JTabbedPane 等
 *
 * 注意: 由于 Swing 需要图形环境, 在无头(headless)环境中无法实际显示窗口。
 * 本测试类仅展示组件的创建和配置代码结构, 不实际显示窗口(或快速创建后 dispose)。
 *
 * @author lingwh
 * @date 2026/08/05 19:03
 */
public class SwingTest {

    /**
     * 测试创建和配置 JFrame 窗口
     */
    @Test
    public void testJFrame() {
        // 创建 JFrame 窗口
        JFrame frame = new JFrame("Swing 测试窗口");

        // 设置窗口大小
        frame.setSize(400, 300);

        // 设置窗口居中显示
        frame.setLocationRelativeTo(null);

        // 设置默认关闭操作
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置窗口最小尺寸
        frame.setMinimumSize(new Dimension(300, 200));

        // 设置窗口是否可调整大小
        frame.setResizable(true);

        // 获取内容面板
        Container contentPane = frame.getContentPane();

        // 设置布局管理器
        contentPane.setLayout(new BorderLayout());

        // 添加一个标签
        JLabel label = new JLabel("这是一个 Swing 窗口", SwingConstants.CENTER);
        contentPane.add(label, BorderLayout.CENTER);

        System.out.println("JFrame 创建成功: title=" + frame.getTitle()
                + ", size=" + frame.getWidth() + "x" + frame.getHeight());

        // 不实际显示窗口, 直接释放资源
        frame.dispose();
        System.out.println("JFrame 已释放资源");
    }

    /**
     * 测试创建和配置 JButton 按钮
     */
    @Test
    public void testJButton() {
        // 创建按钮(带文本)
        JButton button1 = new JButton("点击我");

        // 创建按钮(带图标, 这里使用文本代替)
        JButton button2 = new JButton("提交");

        // 设置按钮的提示文本
        button1.setToolTipText("这是一个按钮");

        // 设置按钮是否可用
        button2.setEnabled(false);

        // 设置按钮的文本
        button1.setText("新的按钮文本");

        // 获取按钮的文本
        String text = button1.getText();
        System.out.println("按钮文本: " + text);

        // 创建按钮并设置快捷键
        JButton button3 = new JButton("保存");
        button3.setMnemonic('S'); // Alt+S 快捷键

        // 设置按钮的背景色和前景色
        button1.setBackground(Color.BLUE);
        button1.setForeground(Color.WHITE);

        // 设置按钮的字体
        button1.setFont(new Font("微软雅黑", Font.BOLD, 14));

        System.out.println("按钮 1 创建成功: text=" + button1.getText()
                + ", enabled=" + button1.isEnabled());
        System.out.println("按钮 2 创建成功: text=" + button2.getText()
                + ", enabled=" + button2.isEnabled());
        System.out.println("按钮 3 创建成功: text=" + button3.getText()
                + ", mnemonic=" + button3.getMnemonic());
    }

    /**
     * 测试创建和配置 JLabel 标签
     */
    @Test
    public void testJLabel() {
        // 创建空标签
        JLabel label1 = new JLabel();

        // 创建带文本的标签
        JLabel label2 = new JLabel("用户名:");

        // 创建带文本和对齐方式的标签
        JLabel label3 = new JLabel("密码:", SwingConstants.RIGHT);

        // 设置标签文本
        label1.setText("请输入信息");

        // 获取标签文本
        String text = label1.getText();
        System.out.println("标签文本: " + text);

        // 设置标签的对齐方式
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setVerticalAlignment(SwingConstants.CENTER);

        // 设置标签的字体
        label1.setFont(new Font("宋体", Font.PLAIN, 16));

        // 设置标签的前景色和背景色
        label1.setForeground(Color.RED);
        label1.setBackground(Color.LIGHT_GRAY);
        label1.setOpaque(true); // 设置不透明以显示背景色

        // 设置标签的提示文本
        label1.setToolTipText("这是一个标签");

        // 获取标签的显示尺寸
        Dimension preferredSize = label1.getPreferredSize();
        System.out.println("标签首选尺寸: " + preferredSize.width + "x" + preferredSize.height);

        System.out.println("标签 1 创建成功: text=" + label1.getText());
        System.out.println("标签 2 创建成功: text=" + label2.getText());
        System.out.println("标签 3 创建成功: text=" + label3.getText()
                + ", alignment=" + label3.getHorizontalAlignment());
    }

    /**
     * 测试创建和配置 JTextField 文本输入框
     */
    @Test
    public void testJTextField() {
        // 创建指定列数的文本框
        JTextField textField1 = new JTextField(20);

        // 创建带默认文本的文本框
        JTextField textField2 = new JTextField("请输入内容");

        // 创建带默认文本和列数的文本框
        JTextField textField3 = new JTextField("默认值", 15);

        // 设置文本框的文本
        textField1.setText("Hello, Swing!");

        // 获取文本框的文本
        String text = textField1.getText();
        System.out.println("文本框内容: " + text);

        // 设置是否可编辑
        textField2.setEditable(true);

        // 设置占位提示文本
        textField1.setToolTipText("请输入您的姓名");

        // 设置文本颜色和背景色
        textField1.setForeground(Color.BLACK);
        textField1.setBackground(Color.WHITE);

        // 设置字体
        textField1.setFont(new Font("Consolas", Font.PLAIN, 12));

        // 设置水平对齐方式
        textField1.setHorizontalAlignment(JTextField.LEFT);

        // 获取列数
        int columns = textField1.getColumns();
        System.out.println("文本框列数: " + columns);

        // 获取文本长度
        System.out.println("文本框文本长度: " + textField1.getText().length());

        System.out.println("文本框 1 创建成功: text=" + textField1.getText());
        System.out.println("文本框 2 创建成功: text=" + textField2.getText());
        System.out.println("文本框 3 创建成功: text=" + textField3.getText());
    }

    /**
     * 测试创建包含多个组件的完整 Swing 窗口
     */
    @Test
    public void testSwingCompositeWindow() {
        // 创建窗口
        JFrame frame = new JFrame("登录窗口");
        frame.setSize(350, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 获取内容面板
        Container contentPane = frame.getContentPane();

        // 使用 GridBagLayout 布局
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // 用户名标签
        JLabel userLabel = new JLabel("用户名:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(userLabel, gbc);

        // 用户名输入框
        JTextField userField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        contentPane.add(userField, gbc);

        // 密码标签
        JLabel pwdLabel = new JLabel("密码:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPane.add(pwdLabel, gbc);

        // 密码输入框
        JPasswordField pwdField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        contentPane.add(pwdField, gbc);

        // 登录按钮
        JButton loginButton = new JButton("登录");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(loginButton, gbc);

        System.out.println("复合窗口创建成功");
        System.out.println("  - 组件数量: " + contentPane.getComponentCount());
        System.out.println("  - 标签: " + userLabel.getText() + ", " + pwdLabel.getText());
        System.out.println("  - 按钮: " + loginButton.getText());
        System.out.println("  - 窗口标题: " + frame.getTitle());

        // 不实际显示窗口, 直接释放资源
        frame.dispose();
        System.out.println("窗口已释放资源");
    }

    /**
     * 测试 Swing 的其他常用组件
     */
    @Test
    public void testOtherSwingComponents() {
        // JTextArea: 多行文本区域
        JTextArea textArea = new JTextArea(5, 20);
        textArea.setText("第一行\n第二行\n第三行");
        textArea.setLineWrap(true); // 自动换行
        textArea.setWrapStyleWord(true); // 按单词换行
        System.out.println("JTextArea 创建成功: rows=" + textArea.getRows()
                + ", text=" + textArea.getText().replace("\n", "\\n"));

        // JPasswordField: 密码输入框
        JPasswordField passwordField = new JPasswordField(10);
        passwordField.setText("secret123");
        char[] password = passwordField.getPassword();
        System.out.println("JPasswordField 创建成功: password length=" + password.length);

        // JCheckBox: 复选框
        JCheckBox checkBox = new JCheckBox("同意条款", true);
        checkBox.setSelected(true);
        System.out.println("JCheckBox 创建成功: text=" + checkBox.getText()
                + ", selected=" + checkBox.isSelected());

        // JRadioButton: 单选按钮
        JRadioButton radioButton1 = new JRadioButton("男", true);
        JRadioButton radioButton2 = new JRadioButton("女", false);
        // 将单选按钮添加到 ButtonGroup 中实现互斥
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(radioButton1);
        genderGroup.add(radioButton2);
        System.out.println("JRadioButton 创建成功: 男=" + radioButton1.isSelected()
                + ", 女=" + radioButton2.isSelected());

        // JComboBox: 下拉框
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("北京");
        comboBox.addItem("上海");
        comboBox.addItem("广州");
        comboBox.addItem("深圳");
        comboBox.setSelectedIndex(0);
        System.out.println("JComboBox 创建成功: itemCount=" + comboBox.getItemCount()
                + ", selected=" + comboBox.getSelectedItem());

        // JList: 列表
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("Java");
        listModel.addElement("Python");
        listModel.addElement("C++");
        JList<String> list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        System.out.println("JList 创建成功: modelSize=" + listModel.getSize());

        // JScrollPane: 滚动面板
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        System.out.println("JScrollPane 创建成功: verticalPolicy="
                + scrollPane.getVerticalScrollBarPolicy());
    }
}