package org.bluebridge.structure.adapter.adapter_f.dao;

import org.bluebridge.structure.adapter.adapter_f.domain.Gatzl;

/**
 * 公安厅指令Dao
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
