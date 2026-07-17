package headfirst.designpatterns.observer.weather;

/**
 * 观察者接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface Observer {

    void update(float temp, float humidity, float pressure);
}
