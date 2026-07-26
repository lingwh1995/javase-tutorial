package headfirst.designpatterns.observer.simple;

/**
 * 观察者模式示例
 *
 * @author lingwh
 * @date 2023/12/7 20:24
 */
public class Example {

    public static void main(String[] args) {
        SimpleSubject simpleSubject = new SimpleSubject();

        SimpleObserver simpleObserver = new SimpleObserver(simpleSubject);

        simpleSubject.setValue(80);
    }
}
