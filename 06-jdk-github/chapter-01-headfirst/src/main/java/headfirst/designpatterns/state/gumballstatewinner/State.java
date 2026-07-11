package headfirst.designpatterns.state.gumballstatewinner;

/**
 * @author lingwh
 * @desc 状态接口
 * @date 2026/7/9 00:00
 */
public interface State {

    void insertQuarter();

    void ejectQuarter();

    void turnCrank();

    void dispense();

    void refill();
}
