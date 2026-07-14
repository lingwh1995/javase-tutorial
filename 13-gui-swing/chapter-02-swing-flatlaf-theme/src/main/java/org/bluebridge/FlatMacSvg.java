package org.bluebridge;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import java.awt.*;
import javax.swing.*;

/**
 * FlatLaf Mac风格SVG图标展示
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class FlatMacSvg {

    public static void main(String[] args) {
        // 设置 macOS 皮肤
        FlatMacLightLaf.setup();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("SVG 图标展示 - macOS 风格");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 600);

            // 1. 创建带图标的列表模型
            // 注意：FlatSVGIcon 的构造函数接受资源路径，你可以去 GitHub 下载开源的图标集放置在 resources/icons/ 下
            DefaultListModel<String> model = new DefaultListModel<>();
            model.addElement("  iCloud Drive");
            model.addElement("  应用中心");
            model.addElement("  桌面");
            model.addElement("  文稿");
            model.addElement("  下载");

            JList<String> list = new JList<>(model);

            // 2. 使用渲染器将 SVG 图标绘制到列表项中
            list.setCellRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                              boolean isSelected, boolean cellHasFocus) {
                    JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                    // 根据索引模拟不同的图标（实际开发中建议封装一个 Icon 对象）
                    String iconName = "home.svg"; // 假设你有这些 svg 文件
                    if (index == 1) {
                        iconName = "app.svg";
                    }
                    if (index == 4) {
                        iconName = "download.svg";
                    }

                    // 核心代码：加载 SVG 并指定大小
                    // 这里由于没有真实文件，我用代码逻辑演示
                    label.setIcon(new FlatSVGIcon("svg/" + iconName, 18, 18));

                    label.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 10));
                    return label;
                }
            });

            // 3. macOS 特有的边距和圆角属性
            list.putClientProperty("JList.selectionArc", 10);
            list.putClientProperty("JList.selectionInsets", new Insets(2, 8, 2, 8));

            frame.add(new JScrollPane(list));
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
