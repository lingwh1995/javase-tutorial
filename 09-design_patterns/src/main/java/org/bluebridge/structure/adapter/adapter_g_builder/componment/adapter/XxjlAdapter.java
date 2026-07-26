package org.bluebridge.structure.adapter.adapter_g_builder.componment.adapter;

import org.bluebridge.structure.adapter.adapter_g_builder.dao.*;
import org.bluebridge.structure.adapter.adapter_g_builder.domain.Xxjl;
import org.bluebridge.structure.adapter.adapter_g_builder.service.IXxjlService;

/**
 * 信息交流适配器
 *
 * @author lingwh
 * @date 2026/7/22 14:08
 */
public class XxjlAdapter implements IXxjlService {

    // 发件人
    private IXxjlDao xxjlFjrDao = new XxjlFjrDao();
    // 收件人
    private IXxjlDao xxjlSjrDao = new XxjlSjrDao();
    // Openfire
    private XxjlOpenfireDao xxjlOpenfireDao = new XxjlOpenfireDao();
    // 公安厅指令 Dao
    private GatzlDao gatzlDao = new GatzlDao();
    // 通知通告 Dao
    private TztgDao tztgDao = new TztgDao();

    @Override
    public void save(Xxjl xxjl) throws Exception {
        // 保存发件人
        xxjlFjrDao.save(xxjl);
        // 保存收件人
        xxjlSjrDao.save(xxjl);
        // openfire 发送通知
        xxjlOpenfireDao.sendXxjlNotice(xxjl);
        // 发送短信
        gatzlDao.sendMsg("123456789");
        // 发送邮件
        tztgDao.sendEmail("123@qq.com");
    }

    @Override
    public void delete(Xxjl xxjl) throws Exception {
        // 删除发件人
        xxjlFjrDao.delete(xxjl);
        // 删除收件人
        xxjlSjrDao.delete(xxjl);
        // openfire 发送通知
        xxjlOpenfireDao.sendXxjlNotice(xxjl);
        // 发送短信
        gatzlDao.sendMsg("123456789");
        // 发送邮件
        tztgDao.sendEmail("123@qq.com");
    }
}
