package org.bluebridge.create.abstractfactory.abstractfactory_d.service;

import org.bluebridge.create.abstractfactory.abstractfactory_d.domain.Cusomer;

/**
 * 客户服务接口
 *
 * @author lingwh
 * @date 2026/7/22 12:55
 */
public interface ICustomerService {

    Cusomer get(Integer uid);
}
