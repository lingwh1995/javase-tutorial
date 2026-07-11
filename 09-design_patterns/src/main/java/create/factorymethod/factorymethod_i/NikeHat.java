package create.factorymethod.factorymethod_i;

/**
 * @author lingwh
 * @desc Nike帽子
 * @date 2026/7/9 00:00
 */
public class NikeHat implements IHat {

    @Override
    public void produce() {
        System.out.println("Nike Hat produce ok...");
    }
}
