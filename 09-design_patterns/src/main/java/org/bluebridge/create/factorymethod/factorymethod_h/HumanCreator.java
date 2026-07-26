package org.bluebridge.create.factorymethod.factorymethod_h;

/**
 * 人类创建者抽象类
 *
 * @author lingwh
 * @date 2026/7/22 11:56
 */
public abstract class HumanCreator {

    /**
     * 工厂方法
     *
     * @return
     */
    abstract Human createHuman();

    public void showSkinColor() {
        Human human = createHuman();
        String skinColor = human.skinColor;
        System.out.println("skinColor:" + skinColor);
    }
}
