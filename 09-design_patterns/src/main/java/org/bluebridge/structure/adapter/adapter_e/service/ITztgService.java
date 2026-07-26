package org.bluebridge.structure.adapter.adapter_e.service;

import org.bluebridge.structure.adapter.adapter_e.domain.Tztg;

/**
 * 通知通告接口
 *
 * @author lingwh
 * @date 2026/7/22 15:21
 */
public interface ITztgService {

    void save(Tztg tztg);

    boolean delete(Tztg tztg);

    boolean sendEmail(String email);
}
