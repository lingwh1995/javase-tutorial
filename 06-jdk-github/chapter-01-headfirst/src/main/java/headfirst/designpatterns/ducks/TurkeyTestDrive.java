package headfirst.designpatterns.ducks;

/**
 * 火鸡测试驱动
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class TurkeyTestDrive {

    public static void main(String[] args) {
        MallardDuck duck = new MallardDuck();
        Turkey duckAdapter = new DuckAdapter(duck);

        for (int i = 0; i < 10; i++) {
            System.out.println("The DuckAdapter says...");
            duckAdapter.gobble();
            duckAdapter.fly();
        }
    }
}
