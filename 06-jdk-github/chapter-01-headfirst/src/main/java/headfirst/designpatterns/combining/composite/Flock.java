package headfirst.designpatterns.combining.composite;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 鸭群组合模式
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Flock implements Quackable {

    ArrayList<Quackable> quackers = new ArrayList<Quackable>();

    public void add(Quackable quacker) {
        quackers.add(quacker);
    }

    public void quack() {
        Iterator<Quackable> iterator = quackers.iterator();
        while (iterator.hasNext()) {
            Quackable quacker = iterator.next();
            quacker.quack();
        }
    }

    public String toString() {
        return "Flock of Quackers";
    }
}
