package org.bluebridge.ioc.anno_one.entity;

import org.bluebridge.ioc.anno_one.anno.Repository;

/**
 * @author lingwh
 * @desc 用户 DAO
 * @date 2019/3/20 00:00
 */
@Repository
public class UserDao {

    public void say() {
        System.out.println("sqy()....");
    }
}
