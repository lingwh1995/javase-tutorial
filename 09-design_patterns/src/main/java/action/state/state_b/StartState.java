package action.state.state_b;

/**
 * @author lingwh
 * @desc 开始状态
 * @date 2019/8/2 8:50
 */
public class StartState implements State {

    @Override
    public void doAction(Context context) {
        System.out.println("Player is in list state");
        context.setState(this);
    }

    @Override
    public String toString() {
        return "Start State";
    }
}
