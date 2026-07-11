package action.visitor.visitor_b;

/**
 * @author lingwh
 * @desc 男人
 * @date 2026/7/9 00:00
 */
public class Man extends Person {
    @Override
    public void accept(Action action) {
        action.getManResult(this);
    }
}
