package org.bluebridge.ioc.anno_one.entity;

import org.bluebridge.ioc.anno_one.anno.Controller;

/**
 * 用户控制器
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
@Controller
public class UserController {

    private IUserService userService;

    public void say() {
        userService.say();
    }
}
