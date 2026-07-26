package headfirst.designpatterns.decorator.starbuzz;

/**
 * 综合咖啡
 *
 * @author lingwh
 * @date 2023/12/7 14:36
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
