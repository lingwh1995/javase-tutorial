package org.bluebridge.structure.adapter.adapter_g_builder.dao;

import org.bluebridge.structure.adapter.adapter_g_builder.domain.Gatzl;

/**
 * GatzlDao
 *
 * @author lingwh
 * @date 2026/7/22 19:14
 */
public class GatzlDao {

    public void save(Gatzl gatzl) {
        System.out.println("保存公安厅指令......");
    }

    public boolean sendMsg(String phoneNumber) {
        System.out.println("公安厅指令发送短信......");
        return true;
    }

    public boolean delete(Gatzl gatzl) {
        System.out.println("删除公安厅指令......");
        return true;
    }
}
