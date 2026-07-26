package org.bluebridge.create.factorymethod.factorymethod_h;

/**
 * 黑色人种创建者
 *
 * @author lingwh
 * @date 2026/7/22 13:27
 */
public class BlackHumanCreator extends HumanCreator {

    @Override
    Human createHuman() {
        return new BlackHuman();
    }
}
