package structure.bridge.bridge_a;

/**
 * @author lingwh
 * @desc 品牌接口
 * @date 2019/3/23 00:00
 */
public interface Brand {
    void sale();
}

/**
 * @author lingwh
 * @desc 联想电脑
 * @date 2019/3/23 00:05
 */
class Lenovol implements Brand {

    @Override
    public void sale() {
        System.out.println("销售联想电脑...");
    }
}

/**
 * @author lingwh
 * @desc Dell电脑
 * @date 2019/3/23 00:08
 */
class Dell implements Brand {

    @Override
    public void sale() {
        System.out.println("销售Dell电脑......");
    }
}
