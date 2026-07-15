package action.visitor.visitor_b;

/**
 * 男人
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Man extends Person {

    @Override
    public void accept(Action action) {
        action.getManResult(this);
    }
}
