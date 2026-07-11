package create.abstractfactory.abstractfactory_g;

/**
 * @author lingwh
 * @desc 创建主板的简单工厂
 * @date 2019/9/4 9:49
 */
public class MainboardFactory {
    /**
     * 创建主板接口对象的方法
     *
     * @param type 选择主板类型的参数
     * @return 主板接口对象的方法
     */
    public static MainboardApi createMainboardApi(int type) {
        MainboardApi mainboard = null;
        // 根据参数来选择并创建相应的主板对象
        if (type == 1) {
            mainboard = new GAMainboard(1156);
        } else if (type == 2) {
            mainboard = new MSIMainboard(939);
        }
        return mainboard;
    }
}
