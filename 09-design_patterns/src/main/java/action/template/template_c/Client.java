package action.template.template_c;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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

        // 制作纯豆浆
        System.out.println("---------------------------");
        PureSoyaMilk pureSoyaMilk = new PureSoyaMilk();
        pureSoyaMilk.make();
    }
}
