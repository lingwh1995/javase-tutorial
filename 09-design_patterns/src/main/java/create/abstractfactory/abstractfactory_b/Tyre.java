package create.abstractfactory.abstractfactory_b;

/**
 * @author lingwh
 * @desc 轮胎接口
 * @date 2019/3/11 00:00
 */
public interface Tyre {
    void roll();
}

class LuxuryTyre implements Tyre {

    public void roll() {
        System.out.println("高端轮胎轮胎滚动磨损小...");
    }
}

class LowTyre implements Tyre {

    @Override
    public void roll() {
        System.out.println("低端轮胎轮胎滚动磨损大...");
    }
}
