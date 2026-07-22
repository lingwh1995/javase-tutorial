package org.bluebridge.ioc.section_03_anno.entity;

import org.bluebridge.ioc.section_03_anno.anno.Repository;

/**
 * 用户 DAO
 *
 * @author lingwh
 * @date2019/3/20 19:02
 */
@Repository
public class UserDao {

    public void say() {
        System.out.println("sqy()....");
    }
}
