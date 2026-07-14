package action.visitor.visitor_b;

/**
 * 失败评价
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Fail extends Action {

    @Override
    public void getManResult(Man man) {
        System.out.println("男人给的评价是该歌手很失败.....");
    }

    @Override
    public void getWomanResult(Woman man) {
        System.out.println("女人给的评价是该歌手很失败.....");
    }
}
