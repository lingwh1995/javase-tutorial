package org.bluebridge.create.abstractfactory.abstractfactory_b;

/**
 * 轮胎接口
 *
 * @author lingwh
 * @date 2019/3/11 19:02
 */
public interface Tyre {

    void roll();
}

class LuxuryTyre implements Tyre {

    public void roll() {
        System.out.println("高端轮胎轮胎滚动磨损小...");
    }
}

class LowTyre implements Tyre {

    @Override
    public void roll() {
        System.out.println("低端轮胎轮胎滚动磨损大...");
    }
}
