package headfirst.designpatterns.decorator.starbuzzWithSizes;

/**
 * 带尺寸的饮料
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public abstract class Beverage {

    public enum Size {
        TALL,
        GRANDE,
        VENTI
    };

    Size size = Size.TALL;
    String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Size getSize() {
        return this.size;
    }

    public abstract double cost();
}
