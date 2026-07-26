package headfirst.designpatterns.combining.ducks;

/**
 * 鸭子鸣叫器
 *
 * @author lingwh
 * @date 2023/12/7 11:42
 */
public class DuckCall implements Quackable {

    @Override
    public void quack() {
        System.out.println("Kwak");
    }
}
