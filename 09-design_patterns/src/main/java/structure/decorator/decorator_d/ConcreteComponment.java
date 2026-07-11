package structure.decorator.decorator_d;

/**
 * @author lingwh
 * @desc 具体组件
 * @date 2019/7/25 17:01
 */
public class ConcreteComponment extends Bread {

    @Override
    public String getDesc() {
        return "普通面包";
    }

    @Override
    public double getPrice() {
        return 2.5;
    }
}
