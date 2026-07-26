package org.bluebridge.structure.flyweight.flyweight_a;

/**
 * 享元模式的外部状态
 *
 * @author lingwh
 * @date 2026/7/22 08:58
 */
public class User {

    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
