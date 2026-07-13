package create.factorymethod.factorymethod_h;

/**
 * 黑色人种创建者
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class BlackHumanCreator extends HumanCreator {

    @Override
    Human createHuman() {
        return new BlackHuman();
    }
}
