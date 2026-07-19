package org.bluebridge.ioc.anno_one.entity;

import org.bluebridge.ioc.anno_one.anno.Service;

/**
 * 用户服务
 *
 * @author lingwh
 * @date 2019/3/20 19:02
 */

@Service
public class UserService implements IUserService {

    private UserDao userDao;

    public void say() {
        userDao.say();
    }
}
