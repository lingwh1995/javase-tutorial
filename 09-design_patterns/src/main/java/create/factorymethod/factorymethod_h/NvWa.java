package create.factorymethod.factorymethod_h;

/**
 * @author lingwh
 * @desc 女娲造人
 * @date 2026/7/9 00:00
 */
public class NvWa {
    public void showSkinColor(String targetSkinColor) {
        Human human = createHuman(targetSkinColor);
        String skinColor = human.skinColor;
        System.out.println("skinColor:" + skinColor);
    }

    Human createHuman(String skinColor) {
        Human human = null;
        if ("white".equals(skinColor)) {
            human = new WhiteHuman();
        }
        if ("yellow".equals(skinColor)) {
            human = new YellowHuman();
        }
        if ("black".equals(skinColor)) {
            human = new BlackHuman();
        }
        return human;
    }
}
