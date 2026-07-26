package headfirst.designpatterns.decorator.starbuzzWithSizes;

/**
 * 综合咖啡
 *
 * @author lingwh
 * @date 2023/12/7 10:29
 */
public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return .89;
    }
}
