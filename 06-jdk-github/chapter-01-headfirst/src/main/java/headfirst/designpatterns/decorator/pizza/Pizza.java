package headfirst.designpatterns.decorator.pizza;

/**
 * 披萨
 *
 * @author lingwh
 * @date 2023/12/7 17:43
 */
public abstract class Pizza {

    String description = "Basic Pizza";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
