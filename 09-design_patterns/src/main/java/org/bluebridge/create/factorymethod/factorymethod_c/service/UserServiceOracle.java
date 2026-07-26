package org.bluebridge.create.factorymethod.factorymethod_c.service;

import org.bluebridge.create.factorymethod.factorymethod_c.domain.User;

/**
 * Oracle 用户服务
 *
 * @author lingwh
 * @date 2026/7/22 15:31
 */
public class UserServiceOracle implements IUserService {

    @Override
    public User get(Integer uid) {
        System.out.println("Oracel操作数据库......");
        return new User();
    }
}
