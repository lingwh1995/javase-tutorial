package headfirst.designpatterns.combining.observer;

/**
 * 可观察叫声接口
 *
 * @author lingwh
 * @date 2023/12/7 16:18
 */
public interface QuackObservable {

    void registerObserver(Observer observer);

    void notifyObservers();
}
