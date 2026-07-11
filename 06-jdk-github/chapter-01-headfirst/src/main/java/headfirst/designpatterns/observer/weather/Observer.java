package headfirst.designpatterns.observer.weather;

/**
 * @author lingwh
 * @desc 观察者接口
 * @date 2026/7/9 00:00
 */
public interface Observer {
    void update(float temp, float humidity, float pressure);
}
