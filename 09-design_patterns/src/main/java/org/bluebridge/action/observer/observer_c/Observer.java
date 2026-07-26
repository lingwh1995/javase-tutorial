package org.bluebridge.action.observer.observer_c;

/**
 * 观察者接口：由具体观察者实现
 */
public interface Observer {

    void update(String temperature, String pressure, String humidity);
}
