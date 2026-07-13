package structure.bridge.bridge_a;

/**
 * 测试桥接模式
 *
 * @author lingwh
 * @date 2019/3/23 19:02
 */
public class Client {

    public static void main(String[] args) {
        // 销售联想笔记本
        Computer2 lenovolLaptop = new Laptop2(new Lenovol());
        lenovolLaptop.sale();

        // 销售Dell台式机
        Computer2 dellDesktop = new Desktop2(new Dell());
        dellDesktop.sale();

        // 销售Dell平板电脑
        Computer2 dellPad = new Pad2(new Dell());
        dellPad.sale();
    }
}
