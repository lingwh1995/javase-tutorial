package org.bluebridge.create.factorymethod.factorymethod_c.service;

import org.bluebridge.create.factorymethod.factorymethod_c.domain.User;

/**
 * 用户服务接口
 *
 * @author lingwh
 * @date 2026/7/22 13:55
 */
public interface IUserService {

    User get(Integer uid);
}
