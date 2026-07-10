package create.factorymethod.factorymethod_i;

/**
 * @author lingwh
 * @desc Adidas帽子
 * @date 2026/7/9 00:00
 */
public class AdidasHat implements IHat {

    @Override
    public void produce() {
        System.out.println("Adidas Hat produce ok...");
    }
}
