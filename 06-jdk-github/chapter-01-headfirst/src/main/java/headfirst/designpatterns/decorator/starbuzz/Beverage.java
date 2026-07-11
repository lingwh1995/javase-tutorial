package headfirst.designpatterns.decorator.starbuzz;

/**
 * @author lingwh
 * @desc 饮料
 * @date 2026/7/9 00:00
 */
public abstract class Beverage {

    String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
