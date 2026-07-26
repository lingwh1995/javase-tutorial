package headfirst.designpatterns.adapter.ducks;

/**
 * 野火鸡实现类
 *
 * @author lingwh
 * @date 2023/12/7 13:19
 */
public class WildTurkey implements Turkey {

    @Override
    public void gobble() {
        System.out.println("Gobble gobble");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying a short distance");
    }
}
