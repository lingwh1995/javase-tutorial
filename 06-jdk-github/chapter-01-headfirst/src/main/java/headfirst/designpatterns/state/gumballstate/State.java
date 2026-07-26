package headfirst.designpatterns.state.gumballstate;

/**
 * 状态接口
 *
 * @author lingwh
 * @date 2023/12/7 08:13
 */
public interface State {

    void insertQuarter();

    void ejectQuarter();

    void turnCrank();

    void dispense();

    void refill();
}
