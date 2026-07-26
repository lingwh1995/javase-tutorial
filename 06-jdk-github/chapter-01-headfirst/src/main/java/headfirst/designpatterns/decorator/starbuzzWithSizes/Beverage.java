package headfirst.designpatterns.decorator.starbuzzWithSizes;

/**
 * 带尺寸的饮料
 *
 * @author lingwh
 * @date 2023/12/7 20:17
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
