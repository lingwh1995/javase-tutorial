package org.bluebridge.ioc.section_03_anno.entity;

import org.bluebridge.ioc.section_03_anno.anno.Controller;

/**
 * 用户控制器
 *
 * @author lingwh
 * @date 2019/3/15 10:30
 */
@Controller
public class UserController {

    private IUserService userService;

    public void say() {
        userService.say();
    }
}
