package org.bluebridge.create.factorymethod.factorymethod_c.service;

import org.bluebridge.create.factorymethod.factorymethod_c.domain.User;

/**
 * 用户服务接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface IUserService {

    User get(Integer uid);
}
