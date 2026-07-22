package org.bluebridge.structure.adapter.adapter_f.service;

import org.bluebridge.structure.adapter.adapter_f.domain.Xxjl;

/**
 * 信息交流接口 - 复用公安厅指令模块的发送短信和通知通告模块发送邮件的API
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface IXxjlService {

    void save(Xxjl xxjl);

    boolean delete(Xxjl xxjl);
}
