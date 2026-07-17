package headfirst.designpatterns.state.gumballstate;

/**
 * 状态接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface State {

    void insertQuarter();

    void ejectQuarter();

    void turnCrank();

    void dispense();

    void refill();
}
