package headfirst.designpatterns.combining.adapter;

/**
 * 鸭子叫声
 *
 * @author lingwh
 * @date 2023/12/7 17:25
 */
public class DuckCall implements Quackable {

    @Override
    public void quack() {
        System.out.println("Kwak");
    }
}
