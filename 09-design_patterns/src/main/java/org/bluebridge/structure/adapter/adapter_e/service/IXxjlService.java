package org.bluebridge.structure.adapter.adapter_e.service;

import org.bluebridge.structure.adapter.adapter_e.domain.Xxjl;

/**
 * 信息交流接口 - 复用公安厅指令模块的发送短信和通知通告模块发送邮件的 API
 *
 * @author lingwh
 * @date 2026/7/22 14:05
 */
public interface IXxjlService {

    void save(Xxjl xxjl);

    boolean delete(Xxjl xxjl);
}
