package org.bluebridge.structure.facade.facade_a;

/**
 * 投影仪，使用饿汉式
 *
 * @author lingwh
 * @date 2026/7/22 14:38
 */
public class Projector {

    private static Projector projector = new Projector();

    public static Projector getInstance() {
        return projector;
    }

    /**
     * 开启投影仪
     */
    public void on() {
        System.out.println("projector on......");
    }

    /**
     * 关闭投影仪
     */
    public void off() {
        System.out.println("projector off......");
    }
}
