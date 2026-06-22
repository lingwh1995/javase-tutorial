package org.bluebridge;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author think
 * @desc 自定义组件（需要自己绘制自定义组件的样式）
 * @date 2026/1/30 18:10
 */
public class _019_CustomComponentTest {

    public static void main(String[] args) {
        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - lambda 表达式调用
        // SwingUtilities.invokeLater(() -> new _019_CustomComponentTest().createAndShowGUI());

        // 使用 invokeLater 将 UI 任务推送到事件分发线程 (EDT) - 方法引用调用
        SwingUtilities.invokeLater(new _019_CustomComponentTest()::createAndShowGUI);
    }

    /**
     * 创建并显示 GUI
     */
    private void createAndShowGUI() {
        // 创建窗体
        Frame frame = new Frame();
        // 设置窗体位置和尺寸
        frame.setBounds(500, 500, 650, 400);
        // 禁用布局管理器，使用绝对定位
        frame.setLayout(null);

        // 添加自定义组件
        MyCustomComponent myCustomComponent = new MyCustomComponent();
        myCustomComponent.setBounds(50, 100, 250, 250);
        frame.add(myCustomComponent);


        /**
         * 添加自定义的图片视图组件
         */
        ImageView imageView = null;
        try {
            // 注意：运行报错的话把当前路径下 gas_meter_dial.jpeg 复制到D盘指定位置
            //String imagePath = "D:\\images\\gas_meter_dial.jpeg";
            //InputStream inputStream = new FileInputStream(imagePath);

            // 也可以直接获取resources下的gas_meter_dial.jpeg的路径
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("gas_meter_dial.jpeg");
            imageView = new ImageView(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        imageView.setBounds(350, 100, 250, 250);
        frame.add(imageView);

        // 设置窗体可见
        frame.setVisible(true);
    }

}

/**
 * 自定义一个组件 继承自Component表示是一个组件
 */
class MyCustomComponent extends Component {

    public MyCustomComponent(){}

    /**
     * 重写绘制方法
     * @param g the graphics context to use for painting
     */
    /*
    @Override
    public void paint(Graphics g) {
        // 我们可以先将画笔切换为黑色
        g.setColor(Color.BLACK);
        // drawRect就是绘制矩形区域，这里的x和y是相对于当前组件的位置来的
        g.fillRect(0, 0, getWidth(), getHeight());
    }*/

    /**
     * 重写绘制方法
     * @param g the graphics context to use for painting
     */
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        //画笔改成红色
        g.setColor(Color.RED);
        //在中间画个圆角矩形边框
        g.drawRoundRect(getWidth() / 4, getHeight() / 4, getWidth() / 2, getHeight() / 2, 30, 30);
    }

}

/**
 * 自定义图片视图组件
 */
class ImageView extends Component {

    private final Image image;

    public ImageView(InputStream inputStream) throws IOException {
        // 我们可以使用ImageIO类来快速将图片文件读取为Image对象
        image = ImageIO.read(inputStream);
        // 注意，在MacOS下加上下面一行，程序就不会报错了
        // Application.getApplication().setDockIconImage(image);
    }

    @Override
    public void paint(Graphics g) {
        // 绘制图片需要提供Image对象
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }
}

/**
 * Graphics接口提供的所有功能如下
 */

/**
 * public abstract class Graphics {
 *    	//移动画笔原点到指定坐标，默认是(0,0)
 *     public abstract void translate(int x, int y);
 *     //设定画笔颜色
 *     public abstract void setColor(Color c);
 *     //设置为普通绘画模式
 *     public abstract void setPaintMode();
 *     //交替颜色模式，比较高级，小伙伴自行了解
 *     public abstract void setXORMode(Color c1);
 *     //设置字体，绘制文本内容时就按照这个字体来绘制
 *     public abstract void setFont(Font font);
 *
 *     //设置裁剪区域，一旦设置裁剪区域，那么裁剪区域以外的地方即使绘制，也不会生效，绘制
 *   	//只会在裁剪区域内生效（有点像图层蒙版？）
 *     public abstract void setClip(int x, int y, int width, int height);
 *     //设定自定义形状的裁剪区域
 *     public abstract void setClip(Shape clip);
 *
 *     //拷贝指定区域的内容到另一个位置
 *     public abstract void copyArea(int x, int y, int width, int height,
 *                                   int dx, int dy);
 *     //绘制直线
 *     public abstract void drawLine(int x1, int y1, int x2, int y2);
 *     //填充矩形区域
 *     public abstract void fillRect(int x, int y, int width, int height);
 *     //绘制矩形边框
 *     public void drawRect(int x, int y, int width, int height);
 * 		//绘制圆角矩形边框
 *     public abstract void drawRoundRect(int x, int y, int width, int height,
 *                                        int arcWidth, int arcHeight);
 *     //填充圆角矩形区域
 *     public abstract void fillRoundRect(int x, int y, int width, int height,
 *                                        int arcWidth, int arcHeight);
 *     //绘制3D矩形边框（其实就是加了个深色和浅色边框，有一个视觉效果罢了）
 *     public void draw3DRect(int x, int y, int width, int height,
 *                            boolean raised);
 *     //填充3D矩形区域（同上）
 *     public void fill3DRect(int x, int y, int width, int height,
 *                            boolean raised);
 *     //绘制椭圆形边框
 *     public abstract void drawOval(int x, int y, int width, int height);
 *     //填充椭圆形区域
 *     public abstract void fillOval(int x, int y, int width, int height);
 *     //绘制弧线边框
 *     public abstract void drawArc(int x, int y, int width, int height,
 *                                  int startAngle, int arcAngle);
 * 		//填充扇形区域
 *     public abstract void fillArc(int x, int y, int width, int height,
 *                                  int startAngle, int arcAngle);
 *     //绘制折线（需要提供多个坐标）
 *     public abstract void drawPolyline(int xPoints[], int yPoints[],
 *                                       int nPoints);
 * 		//绘制多边形边框
 *     public abstract void drawPolygon(int xPoints[], int yPoints[],
 *                                      int nPoints);
 *     //填充多边形区域
 *     public abstract void fillPolygon(int xPoints[], int yPoints[],
 *                                      int nPoints);
 *     //绘制文本
 *     public abstract void drawString(String str, int x, int y);
 *    	//绘制图片（绘制大小为图片原本大小）
 *     public abstract boolean drawImage(Image img, int x, int y,
 *                                       ImageObserver observer);
 *    	//绘制按自定义大小缩放后的图片
 *     public abstract boolean drawImage(Image img, int x, int y,
 *                                       int width, int height,
 *                                       ImageObserver observer);
 *     //绘制图片时如果是透明部分则采用背景颜色填充
 *     public abstract boolean drawImage(Image img, int x, int y,
 *                                       Color bgcolor,
 *                                       ImageObserver observer);
 *     //绘制按自定义大小缩放后带背景颜色的图片
 *     public abstract boolean drawImage(Image img, int x, int y,
 *                                       int width, int height,
 *                                       Color bgcolor,
 *                                       ImageObserver observer);
 *     //对原本的图片按照起始坐标和尺寸进行裁剪后，再以给定大小绘制到给定位置
 *     public abstract boolean drawImage(Image img,
 *                                       int dx1, int dy1, int dx2, int dy2,
 *                                       int sx1, int sy1, int sx2, int sy2,
 *                                       ImageObserver observer);
 *     //累了
 *     public abstract boolean drawImage(Image img,
 *                                       int dx1, int dy1, int dx2, int dy2,
 *                                       int sx1, int sy1, int sx2, int sy2,
 *                                       Color bgcolor,
 *                                       ImageObserver observer);
 * }
 */