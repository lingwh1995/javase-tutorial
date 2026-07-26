package headfirst.designpatterns.combining.composite;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 鸭群组合模式
 *
 * @author lingwh
 * @date 2023/12/7 09:18
 */
public class Flock implements Quackable {

    ArrayList<Quackable> quackers = new ArrayList<Quackable>();

    public void add(Quackable quacker) {
        quackers.add(quacker);
    }

    @Override
    public void quack() {
        Iterator<Quackable> iterator = quackers.iterator();
        while (iterator.hasNext()) {
            Quackable quacker = iterator.next();
            quacker.quack();
        }
    }

    @Override
    public String toString() {
        return "Flock of Quackers";
    }
}
