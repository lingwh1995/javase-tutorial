package headfirst.designpatterns.observer.simple;

/**
 * @author lingwh
 * @desc 主题接口
 * @date 2026/7/9 00:00
 */
public interface Subject {
    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers();
}
