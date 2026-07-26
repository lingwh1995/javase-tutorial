package headfirst.designpatterns.proxy.virtualproxy;

import java.awt.*;
import javax.swing.*;

/**
 * 图片组件
 *
 * @author lingwh
 * @date 2023/12/7 12:45
 */
class ImageComponent extends JComponent {

    private static final long serialVersionUID = 1L;
    private Icon icon;

    public ImageComponent(Icon icon) {
        this.icon = icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = icon.getIconWidth();
        int h = icon.getIconHeight();
        int x = (800 - w) / 2;
        int y = (600 - h) / 2;
        icon.paintIcon(this, g, x, y);
    }
}
