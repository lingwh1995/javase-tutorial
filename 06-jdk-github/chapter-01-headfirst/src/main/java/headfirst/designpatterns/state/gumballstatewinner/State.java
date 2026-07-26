package headfirst.designpatterns.state.gumballstatewinner;

/**
 * 状态接口
 *
 * @author lingwh
 * @date 2023/12/7 18:54
 */
public interface State {

    void insertQuarter();

    void ejectQuarter();

    void turnCrank();

    void dispense();

    void refill();
}
