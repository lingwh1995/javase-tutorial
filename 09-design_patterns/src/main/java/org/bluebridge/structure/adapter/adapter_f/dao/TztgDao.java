package org.bluebridge.structure.adapter.adapter_f.dao;

import org.bluebridge.structure.adapter.adapter_f.domain.Tztg;

/**
 * 通知通告Dao
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class TztgDao {

    public void save(Tztg tztg) {
        System.out.println("保存通知通告......");
    }

    public boolean sendEmail(String email) {
        System.out.println("通知通告发送邮件......");
        return true;
    }

    public boolean delete(Tztg tztg) {
        System.out.println("删除通知通告......");
        return true;
    }
}
