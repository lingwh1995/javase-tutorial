package headfirst.designpatterns.decorator.starbuzzWithSizes;

/**
 * @author lingwh
 * @desc 带尺寸的饮料
 * @date 2026/7/9 00:00
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
