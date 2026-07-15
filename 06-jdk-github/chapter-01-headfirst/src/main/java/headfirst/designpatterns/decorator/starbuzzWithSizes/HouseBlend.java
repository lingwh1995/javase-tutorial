package headfirst.designpatterns.decorator.starbuzzWithSizes;

/**
 * 综合咖啡
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "House Blend Coffee";
    }

    public double cost() {
        return .89;
    }
}
