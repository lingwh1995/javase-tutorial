package org.bluebridge.create.simplefactory.simplefactory_e;

/**
 * 人类创建者
 *
 * @author lingwh
 * @date 2026/7/22 15:38
 */
public class HumanCreator {

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
