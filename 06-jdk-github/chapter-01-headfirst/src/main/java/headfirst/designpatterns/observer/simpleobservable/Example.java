package headfirst.designpatterns.observer.simpleobservable;

/**
 * 观察者模式示例
 *
 * @author lingwh
 * @date 2023/12/7 09:15
 */
public class Example {

    public static void main(String[] args) {
        SimpleSubject simpleSubject = new SimpleSubject();

        SimpleObserver simpleObserver = new SimpleObserver(simpleSubject);

        simpleSubject.setValue(80);
    }
}
