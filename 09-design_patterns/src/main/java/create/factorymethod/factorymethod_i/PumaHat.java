package create.factorymethod.factorymethod_i;

/**
 * @author lingwh
 * @desc Puma帽子
 * @date 2026/7/9 00:00
 */
public class PumaHat implements IHat {

    @Override
    public void produce() {
        System.out.println("Puma Hat produce ok...");
    }
}
