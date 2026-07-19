package headfirst.designpatterns.adapter.ducks;

/**
 * 野火鸡实现类
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class WildTurkey implements Turkey {

    public void gobble() {
        System.out.println("Gobble gobble");
    }

    public void fly() {
        System.out.println("I'm flying a short distance");
    }
}
