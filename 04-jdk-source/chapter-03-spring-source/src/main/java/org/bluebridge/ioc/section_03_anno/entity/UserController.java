package org.bluebridge.ioc.section_03_anno.entity;

import org.bluebridge.ioc.section_03_anno.anno.Controller;

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
