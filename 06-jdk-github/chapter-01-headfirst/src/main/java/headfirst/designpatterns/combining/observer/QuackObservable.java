package headfirst.designpatterns.combining.observer;

/**
 * 可观察叫声接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface QuackObservable {

    void registerObserver(Observer observer);

    void notifyObservers();
}
