package headfirst.designpatterns.ducks;

/**
 * @author lingwh
 * @desc 火鸡测试驱动
 * @date 2026/7/9 00:00
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
