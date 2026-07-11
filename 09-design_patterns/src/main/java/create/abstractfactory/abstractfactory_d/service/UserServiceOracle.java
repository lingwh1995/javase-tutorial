package create.abstractfactory.abstractfactory_d.service;

import create.abstractfactory.abstractfactory_d.domain.User;

/**
 * @author lingwh
 * @desc Oracle用户服务实现
 * @date 2026/7/9 00:00
 */
public class UserServiceOracle implements IUserService {
    @Override
    public User get(Integer uid) {
        System.out.println("Oracel操作数据库......User");
        return new User();
    }
}
