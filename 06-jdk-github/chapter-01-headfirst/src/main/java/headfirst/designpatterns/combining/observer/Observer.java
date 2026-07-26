package headfirst.designpatterns.combining.observer;

/**
 * 观察者接口
 *
 * @author lingwh
 * @date 2023/12/7 14:02
 */
public interface Observer {

    void update(QuackObservable duck);
}
