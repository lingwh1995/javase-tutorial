package headfirst.designpatterns.observer.simple;

/**
 * 简单观察者
 *
 * @author lingwh
 * @date 2023/12/7 19:50
 */
public class SimpleObserver implements Observer {

    private int value;
    private Subject simpleSubject;

    public SimpleObserver(Subject simpleSubject) {
        this.simpleSubject = simpleSubject;
        simpleSubject.registerObserver(this);
    }

    @Override
    public void update(int value) {
        this.value = value;
        display();
    }

    public void display() {
        System.out.println("Value: " + value);
    }
}
