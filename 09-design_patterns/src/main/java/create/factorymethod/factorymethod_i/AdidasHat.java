package create.factorymethod.factorymethod_i;

/**
 * Adidas帽子
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class AdidasHat implements IHat {

    @Override
    public void produce() {
        System.out.println("Adidas Hat produce ok...");
    }
}
