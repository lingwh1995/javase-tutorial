package org.bluebridge.structure.adapter.adapter_g_builder.dao;

import org.bluebridge.structure.adapter.adapter_g_builder.domain.Xxjl;

/**
 * 信息交流收件人数据访问对象
 *
 * @author lingwh
 * @date 2026/7/22 21:33
 */
public class XxjlSjrDao implements IXxjlDao {

    @Override
    public boolean save(Xxjl xxjl) {
        System.out.println("保存收件人接收到的信息交流......");
        return true;
    }

    @Override
    public boolean delete(Xxjl xxjl) {
        System.out.println("删除收件人接受到的信息交流......");
        return true;
    }
}
