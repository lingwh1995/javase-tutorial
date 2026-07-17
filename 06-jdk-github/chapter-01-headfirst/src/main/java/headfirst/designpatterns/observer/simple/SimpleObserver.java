package headfirst.designpatterns.observer.simple;

/**
 * 简单观察者
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class SimpleObserver implements Observer {

    private int value;
    private Subject simpleSubject;

    public SimpleObserver(Subject simpleSubject) {
        this.simpleSubject = simpleSubject;
        simpleSubject.registerObserver(this);
    }

    public void update(int value) {
        this.value = value;
        display();
    }

    public void display() {
        System.out.println("Value: " + value);
    }
}
