package org.bluebridge.create.factorymethod.factorymethod_h;

/**
 * 白色人种创建者
 *
 * @author lingwh
 * @date 2026/7/22 17:15
 */
public class WhiteHumanCreator extends HumanCreator {

    @Override
    Human createHuman() {
        return new WhiteHuman();
    }
}
