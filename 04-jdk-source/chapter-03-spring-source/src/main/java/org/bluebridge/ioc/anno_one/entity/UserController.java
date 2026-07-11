package org.bluebridge.ioc.anno_one.entity;

import org.bluebridge.ioc.anno_one.anno.Controller;

/**
 * @author lingwh
 * @desc 用户控制器
 * @date 2019/3/20 00:00
 */
@Controller
public class UserController {
    private IUserService userService;

    public void say() {
        userService.say();
    }
}
