package org.bluebridge.action.template.template_b;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2026/7/22 15:03
 */
public class Client {

    public static void main(String[] args) {
        // 制作红豆豆浆
        SoyaMilk readbeanSoyaMilk = new ReadbeanSoyaMilk();
        readbeanSoyaMilk.make();

        System.out.println("---------------------------");
        // 制作花生豆浆
        SoyaMilk pennutSoyaMilk = new PennutSoyaMilk();
        pennutSoyaMilk.make();
    }
}
