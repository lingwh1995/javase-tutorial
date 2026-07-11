package headfirst.designpatterns.adapter.ducks;

/**
 * @author lingwh
 * @desc 野火鸡实现类
 * @date 2026/7/9 00:00
 */
public class WildTurkey implements Turkey {
    public void gobble() {
        System.out.println("Gobble gobble");
    }

    public void fly() {
        System.out.println("I'm flying a short distance");
    }
}
