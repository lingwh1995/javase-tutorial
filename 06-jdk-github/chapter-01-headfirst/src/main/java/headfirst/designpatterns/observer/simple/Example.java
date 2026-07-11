package headfirst.designpatterns.observer.simple;

/**
 * @author lingwh
 * @desc 观察者模式示例
 * @date 2026/7/9 00:00
 */
public class Example {

    public static void main(String[] args) {
        SimpleSubject simpleSubject = new SimpleSubject();

        SimpleObserver simpleObserver = new SimpleObserver(simpleSubject);

        simpleSubject.setValue(80);
    }
}
