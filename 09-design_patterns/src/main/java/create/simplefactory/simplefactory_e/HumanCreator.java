package create.simplefactory.simplefactory_e;

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
