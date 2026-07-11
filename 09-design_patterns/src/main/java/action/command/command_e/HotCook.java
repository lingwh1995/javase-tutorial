package action.command.command_e;

/**
 * @author lingwh
 * @desc 做热菜的厨师
 * @date 2019/8/5 13:38
 */
public class HotCook implements CookApi {

    /**
     * 做热菜
     *
     * @param food 做热菜
     */
    @Override
    public void cook(Food food) {
        System.out.println("凉菜" + food.getName() + "已经做好，本厨师正在装盘。");
    }
}
