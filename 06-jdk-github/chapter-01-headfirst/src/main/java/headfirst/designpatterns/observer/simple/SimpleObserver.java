package headfirst.designpatterns.observer.simple;

/**
 * @author lingwh
 * @desc 简单观察者
 * @date 2026/7/9 00:00
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
