package headfirst.designpatterns.ducks;

/**
 * 野生火鸡
 *
 * @author lingwh
 * @date 2023/12/7 11:58
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
