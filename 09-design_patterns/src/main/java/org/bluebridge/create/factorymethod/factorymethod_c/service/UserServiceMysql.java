package org.bluebridge.create.factorymethod.factorymethod_c.service;

import org.bluebridge.create.factorymethod.factorymethod_c.domain.User;

/**
 * Mysql 用户服务
 *
 * @author lingwh
 * @date 2026/7/22 14:18
 */
public class UserServiceMysql implements IUserService {

    @Override
    public User get(Integer uid) {
        System.out.println("Mysql操作数据库......");
        return new User();
    }
}
