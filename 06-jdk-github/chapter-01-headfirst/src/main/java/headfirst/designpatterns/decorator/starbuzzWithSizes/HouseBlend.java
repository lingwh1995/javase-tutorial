package headfirst.designpatterns.decorator.starbuzzWithSizes;

/**
 * @author lingwh
 * @desc 综合咖啡
 * @date 2026/7/9 00:00
 */
public class HouseBlend extends Beverage {
    public HouseBlend() {
        description = "House Blend Coffee";
    }

    public double cost() {
        return .89;
    }
}
