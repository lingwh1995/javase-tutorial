package create.factorymethod.factorymethod_h;

/**
 * 黄色人种创建者
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class YellowHumanCreator extends HumanCreator {

    @Override
    Human createHuman() {
        return new YellowHuman();
    }
}
