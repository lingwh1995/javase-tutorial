package create.factorymethod.factorymethod_h;

/**
 * @author lingwh
 * @desc 黑色人种创建者
 * @date 2026/7/9 00:00
 */
public class BlackHumanCreator extends HumanCreator {

    @Override
    Human createHuman() {
        return new BlackHuman();
    }
}
