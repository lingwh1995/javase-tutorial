package structure.adapter.adapter_e.service;

import structure.adapter.adapter_e.domain.Gatzl;

/**
 * 公安厅指令接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface IGatzlService {

    void save(Gatzl gatzl);

    boolean delete(Gatzl gatzl);

    boolean sendMsg(String phoneNumber);
}
