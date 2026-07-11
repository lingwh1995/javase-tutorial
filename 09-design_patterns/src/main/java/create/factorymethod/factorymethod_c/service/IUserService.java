package create.factorymethod.factorymethod_c.service;

import create.factorymethod.factorymethod_c.domain.User;

/**
 * @author lingwh
 * @desc 用户服务接口
 * @date 2026/7/9 00:00
 */
public interface IUserService {
    User get(Integer uid);
}
