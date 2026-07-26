package org.bluebridge.create.abstractfactory.abstractfactory_d.service;

import org.bluebridge.create.abstractfactory.abstractfactory_d.domain.Cusomer;

/**
 * Oracle 客户服务实现
 *
 * @author lingwh
 * @date 2026/7/22 16:22
 */
public class CustomerServiceOracle implements ICustomerService {

    @Override
    public Cusomer get(Integer uid) {
        System.out.println("Oracle操作数据库......Customer");
        return new Cusomer();
    }
}
