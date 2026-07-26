package org.bluebridge.structure.adapter.adapter_f.service;

import org.bluebridge.structure.adapter.adapter_f.domain.Gatzl;

/**
 * 公安厅指令接口
 *
 * @author lingwh
 * @date 2026/7/22 11:27
 */
public interface IGatzlService {

    void save(Gatzl gatzl);

    boolean delete(Gatzl gatzl);

    boolean sendMsg(String phoneNumber);
}
