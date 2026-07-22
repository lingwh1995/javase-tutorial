package org.bluebridge.structure.decorator.decorator_d;

/**
 * 具体的装饰者:给面包加上曲奇
 *
 * @author lingwh
 * @date 2019/7/25 17:05
 */
public class ConcreteDecorator extends Decorator {

    public ConcreteDecorator(Bread bread) {
        super(bread);
    }

    @Override
    public String getDesc() {
        return super.getDesc() + "\t加一份曲奇";
    }

    @Override
    public double getPrice() {
        return 1.5 + super.getPrice();
    }
}
