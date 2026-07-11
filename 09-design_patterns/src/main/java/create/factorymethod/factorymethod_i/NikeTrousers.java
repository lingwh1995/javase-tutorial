package create.factorymethod.factorymethod_i;

/**
 * @author lingwh
 * @desc Nike裤子
 * @date 2026/7/9 00:00
 */
public class NikeTrousers implements ITrousers {

    @Override
    public void produce() {
        System.out.println("Nike Trousers produce ok...");
    }
}
