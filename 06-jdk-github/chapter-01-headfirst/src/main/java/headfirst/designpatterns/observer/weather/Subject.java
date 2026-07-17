package headfirst.designpatterns.observer.weather;

/**
 * 主题接口
 *
 * @author lingwh
 * @date 2026/7/9 19:02
 */
public interface Subject {

    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers();
}
