package org.bluebridge.structure.adapter.adapter_e.dao;

import org.bluebridge.structure.adapter.adapter_e.domain.Gatzl;

/**
 * 公安厅指令 Dao
 *
 * @author lingwh
 * @date 2026/7/22 13:47
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
