package org.bluebridge.action.memento.mementor_b;

/**
 * 备忘录对象：负责保存好状态变化的记录
 *
 * @author lingwh
 * @date 2026/7/22 15:03
 */
public class Memento {

    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
