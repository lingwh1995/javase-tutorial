package action.strategy.strategy_e;

import action.strategy.strategy_e.controller.UserController;

/**
 * 客户端测试
 *
 * @author lingwh
 * @date 2019/8/5 9:16
 */
public class Client {

    public static void main(String[] args) {
        UserController userController = new UserController();
        userController.save("01,张三,18,上海人");
    }
}
