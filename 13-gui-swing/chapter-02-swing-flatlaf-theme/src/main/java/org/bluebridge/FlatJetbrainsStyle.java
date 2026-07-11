package org.bluebridge;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

/**
 * @author lingwh
 * @desc IntelliJ风格界面示例
 * @date 2026/7/9 00:00
 */
public class FlatJetbrainsStyle {

    // 1. 图标缓存池：避免重复创建对象导致的内存溢出
    private static final Map<String, FlatSVGIcon> ICON_CACHE = new HashMap<>();

    public static void main(String[] args) {
        // 环境预检
        System.setProperty("flatlaf.uiScale", "true");
        FlatDarkLaf.setup();

        // 全局样式微调
        UIManager.put("MenuBar.selectionBackground", UIManager.getColor("Component.accentColor"));

        // 启动应用
        SwingUtilities.invokeLater(FlatJetbrainsStyle::launch);
    }

    private static void launch() {
        // 2. 组合优于继承：直接操作实例而非继承整个 JFrame
        JFrame frame = new JFrame("IntelliJ IDEA 风格示例");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 3. 职责分离：由专门的方法负责菜单构建
        frame.setJMenuBar(buildMenuBar());

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * 3. 职责分离：由专门的方法负责菜单栏构建
     */
    private static JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // 文件菜单
        JMenu fileMenu = new JMenu("文件(F)");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        // 使用缓存获取图标
        fileMenu.add(createMenuItem("新建...", "icons/new.svg", KeyEvent.VK_N));
        fileMenu.add(createMenuItem("打开...", "icons/open.svg", 0));

        menuBar.add(fileMenu);
        menuBar.add(new JMenu("编辑(E)"));
        menuBar.add(new JMenu("视图(V)"));

        return menuBar;
    }

    /**
     * 4. 辅助方法：统一创建菜单项，减少冗余代码
     *
     * @param text
     * @param iconPath
     * @param mnemonic
     * @return
     */
    private static JMenuItem createMenuItem(String text, String iconPath, int mnemonic) {
        // 从缓存中获取图标，显著降低内存抖动
        FlatSVGIcon icon = ICON_CACHE.computeIfAbsent(iconPath, path -> new FlatSVGIcon(path, 16, 16));
        JMenuItem item = new JMenuItem(text, icon);
        if (mnemonic != 0) {
            item.setMnemonic(mnemonic);
        }
        return item;
    }
}
