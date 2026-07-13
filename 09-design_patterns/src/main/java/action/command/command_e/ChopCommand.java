package action.command.command_e;

/**
 * 绿豆排骨汤命令
 *
 * @author lingwh
 * @date 2019/8/5 13:40
 */
public class ChopCommand implements Command {

    /**
     * 持有具体做菜的厨师的对象
     */
    private CookApi cookApi = null;

    /**
     * 设置具体做菜的厨师的对象
     *
     * @param cookApi 具体做菜的厨师的对象
     */
    public void setCookApi(CookApi cookApi) {
        this.cookApi = cookApi;
    }

    @Override
    public void execute() {
        Food food = new Food("hot", "冰镇绿豆排骨汤");
        cookApi.cook(food);
    }
}
