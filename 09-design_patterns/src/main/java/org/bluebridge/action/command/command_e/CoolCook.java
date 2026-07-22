package org.bluebridge.action.command.command_e;

/**
 * 做凉菜的厨师
 *
 * @author lingwh
 * @date 2019/8/5 13:38
 */
public class CoolCook implements CookApi {

    /**
     * 做凉菜
     *
     * @param food 做凉菜
     */
    @Override
    public void cook(Food food) {
        System.out.println("凉菜" + food.getName() + "已经做好，本厨师正在装盘。");
    }
}
