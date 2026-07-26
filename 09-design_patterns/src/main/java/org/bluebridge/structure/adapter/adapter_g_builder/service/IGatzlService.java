package org.bluebridge.structure.adapter.adapter_g_builder.service;

import org.bluebridge.structure.adapter.adapter_g_builder.domain.Gatzl;

/**
 * 公安厅指令服务接口
 *
 * @author lingwh
 * @date 2026/7/22 13:51
 */
public interface IGatzlService {

    void save(Gatzl gatzl);

    boolean delete(Gatzl gatzl);

    boolean sendMsg(String phoneNumber);
}
