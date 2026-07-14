package action.command.command_e;

/**
 * 饺子命令
 *
 * @author lingwh
 * @date 2019/8/5 13:42
 */
public class DumplingCommand implements Command {

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
        Food food = new Food("hot", "水晶蒸饺");
        cookApi.cook(food);
    }
}
