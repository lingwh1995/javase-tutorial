package headfirst.designpatterns.observer.simpleobservable;

import java.util.Observable;

/**
 * 简单主题
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class SimpleSubject extends Observable {

    private int value = 0;

    public SimpleSubject() {}

    public void setValue(int value) {
        this.value = value;
        setChanged();
        notifyObservers(value);
    }

    public int getValue() {
        return this.value;
    }
}
