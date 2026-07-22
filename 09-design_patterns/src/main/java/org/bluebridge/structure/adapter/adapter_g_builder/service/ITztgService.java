package org.bluebridge.structure.adapter.adapter_g_builder.service;

import org.bluebridge.structure.adapter.adapter_g_builder.domain.Tztg;

/**
 * 通知通告服务接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface ITztgService {

    void save(Tztg tztg);

    boolean delete(Tztg tztg);

    boolean sendEmail(String email);
}
