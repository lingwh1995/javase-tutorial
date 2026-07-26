package org.bluebridge.action.template.template_c;

/**
 * 纯豆浆
 *
 * @author lingwh
 * @date 2026/7/22 15:03
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
