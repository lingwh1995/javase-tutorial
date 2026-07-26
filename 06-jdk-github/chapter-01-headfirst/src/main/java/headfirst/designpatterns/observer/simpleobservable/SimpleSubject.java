package headfirst.designpatterns.observer.simpleobservable;

import java.util.Observable;

/**
 * 简单主题
 *
 * @author lingwh
 * @date 2023/12/7 09:52
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
