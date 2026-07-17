package headfirst.designpatterns.decorator.pizza;

/**
 * 披萨
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public abstract class Pizza {

    String description = "Basic Pizza";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
