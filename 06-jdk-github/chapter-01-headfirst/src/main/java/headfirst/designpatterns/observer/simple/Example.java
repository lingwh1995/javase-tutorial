package headfirst.designpatterns.observer.simple;

/**
 * 观察者模式示例
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Example {

    public static void main(String[] args) {
        SimpleSubject simpleSubject = new SimpleSubject();

        SimpleObserver simpleObserver = new SimpleObserver(simpleSubject);

        simpleSubject.setValue(80);
    }
}
