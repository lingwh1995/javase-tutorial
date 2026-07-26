package headfirst.designpatterns.decorator.starbuzz;

/**
 * 饮料
 *
 * @author lingwh
 * @date 2023/12/7 09:31
 */
public abstract class Beverage {

    String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
