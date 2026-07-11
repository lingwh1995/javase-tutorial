package create.factorymethod.factorymethod_h;

/**
 * @author lingwh
 * @desc 白色人种创建者
 * @date 2026/7/9 00:00
 */
public class WhiteHumanCreator extends HumanCreator {
    @Override
    Human createHuman() {
        return new WhiteHuman();
    }
}
