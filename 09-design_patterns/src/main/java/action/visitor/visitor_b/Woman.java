package action.visitor.visitor_b;

/**
 * @author lingwh
 * @desc 女人
 * @date 2026/7/9 00:00
 */
public class Woman extends Person {
    @Override
    public void accept(Action action) {
        action.getWomanResult(this);
    }
}
