package structure.adapter.adapter_g_builder.service;

import structure.adapter.adapter_g_builder.domain.Tztg;

/**
 * @author lingwh
 * @desc 通知通告服务接口
 * @date 2026/7/9 00:00
 */
public interface ITztgService {
    void save(Tztg tztg);

    boolean delete(Tztg tztg);

    boolean sendEmail(String email);
}
