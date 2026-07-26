package org.bluebridge.structure.decorator.decorator_b;

/**
 * 意大利咖啡：被装饰者
 *
 * @author lingwh
 * @date 2026/7/22 14:08
 */
public class ItalyCoffee extends Coffee {

    public ItalyCoffee() {
        super.setDesc("意大利咖啡");
        super.setPrice(109.6);
    }
}
