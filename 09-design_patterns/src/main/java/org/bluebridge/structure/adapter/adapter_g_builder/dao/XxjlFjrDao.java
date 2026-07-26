package org.bluebridge.structure.adapter.adapter_g_builder.dao;

import org.bluebridge.structure.adapter.adapter_g_builder.domain.Xxjl;

/**
 * 信息交流发件人数据访问对象
 *
 * @author lingwh
 * @date 2026/7/22 08:48
 */
public class XxjlFjrDao implements IXxjlDao {

    @Override
    public boolean save(Xxjl xxjl) {
        System.out.println("保存发件人发送的信息交流......");
        return true;
    }

    @Override
    public boolean delete(Xxjl xxjl) {
        System.out.println("删除发件人发送的信息交流......");
        return true;
    }
}
