package action.template.template_c;

/**
 * @author lingwh
 * @desc 纯豆浆
 * @date 2026/7/9 00:00
 */
public class PureSoyaMilk extends SoyaMilk {
    @Override
    void addIngredients() {
        System.out.println("纯豆浆,不加任何佐料......");
    }

    @Override
    public boolean hook() {
        return false;
    }
}
