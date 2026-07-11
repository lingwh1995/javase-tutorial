package headfirst.designpatterns.combining.observer;

/**
 * @author lingwh
 * @desc 可观察叫声接口
 * @date 2026/7/9 00:00
 */
public interface QuackObservable {
    void registerObserver(Observer observer);

    void notifyObservers();
}
