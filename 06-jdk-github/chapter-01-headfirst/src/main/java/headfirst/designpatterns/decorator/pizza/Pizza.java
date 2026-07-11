package headfirst.designpatterns.decorator.pizza;

/**
 * @author lingwh
 * @desc 披萨
 * @date 2026/7/9 00:00
 */
public abstract class Pizza {
    String description = "Basic Pizza";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
