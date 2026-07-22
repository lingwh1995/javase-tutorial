package org.bluebridge.structure.bridge.bridge_a;

/**
 * 品牌接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface Brand {

    void sale();
}

/**
 * 联想电脑
 *
 * @author lingwh
 * @date 2019/3/23 00:05
 */
class Lenovol implements Brand {

    @Override
    public void sale() {
        System.out.println("销售联想电脑...");
    }
}

/**
 * Dell电脑
 *
 * @author lingwh
 * @date 2019/3/23 00:08
 */
class Dell implements Brand {

    @Override
    public void sale() {
        System.out.println("销售Dell电脑......");
    }
}
