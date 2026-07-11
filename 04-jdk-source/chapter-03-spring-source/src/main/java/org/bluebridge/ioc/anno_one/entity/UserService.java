package org.bluebridge.ioc.anno_one.entity;

import org.bluebridge.ioc.anno_one.anno.Service;

/**
 * @author lingwh
 * @desc 用户服务
 * @date 2019/3/20 00:00
 */

@Service
public class UserService implements IUserService {

    private UserDao userDao;

    public void say() {
        userDao.say();
    }
}
