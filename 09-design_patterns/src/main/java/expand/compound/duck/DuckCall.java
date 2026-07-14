package expand.compound.duck;

/**
 * 鸭鸣器
 *
 * @author lingwh
 * @date 2019/10/10 9:45
 */
public class DuckCall implements Quackable {

    @Override
    public void quack() {
        System.out.println("DuckCall mock real Duck Quack......");
    }
}
