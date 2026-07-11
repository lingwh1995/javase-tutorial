package structure.adapter.adapter_g_builder.service;

import structure.adapter.adapter_g_builder.domain.Gatzl;

/**
 * @author lingwh
 * @desc 公安厅指令服务接口
 * @date 2026/7/9 00:00
 */
public interface IGatzlService {
    void save(Gatzl gatzl);

    boolean delete(Gatzl gatzl);

    boolean sendMsg(String phoneNumber);
}
