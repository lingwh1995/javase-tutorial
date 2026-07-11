package action.template.template_b;

/**
 * @author lingwh
 * @desc 客户端
 * @date 2026/7/9 00:00
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
