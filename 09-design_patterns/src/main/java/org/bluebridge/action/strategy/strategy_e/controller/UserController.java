package org.bluebridge.action.strategy.strategy_e.controller;


import org.bluebridge.action.strategy.strategy_e.service.IUserService;
import org.bluebridge.action.strategy.strategy_e.service.UserService;

/**
 * 用户控制器
 *
 * @author lingwh
 * @date 2019/8/5 9:05
 */
public class UserController {

    private IUserService userService = new UserService();

    public void save(String msg) {
        userService.save(msg);
    }
}
