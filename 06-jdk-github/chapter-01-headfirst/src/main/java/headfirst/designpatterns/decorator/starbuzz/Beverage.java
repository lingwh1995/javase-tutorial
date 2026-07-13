package headfirst.designpatterns.decorator.starbuzz;

/**
 * 饮料
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public abstract class Beverage {

    String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
