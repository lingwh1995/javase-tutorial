package headfirst.designpatterns.observer.weather;

/**
 * 观察者接口
 *
 * @author lingwh
 * @date 2023/12/7 20:33
 */
public interface Observer {

    void update(float temp, float humidity, float pressure);
}
