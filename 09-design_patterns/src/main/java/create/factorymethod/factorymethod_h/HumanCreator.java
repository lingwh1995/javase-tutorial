package create.factorymethod.factorymethod_h;

/**
 * @author lingwh
 * @desc 人类创建者抽象类
 * @date 2026/7/9 00:00
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
