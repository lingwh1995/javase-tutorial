package org.bluebridge.structure.adapter.adapter_f.service;

import org.bluebridge.structure.adapter.adapter_f.domain.Tztg;

/**
 * 通知通告接口
 *
 * @author lingwh
 * @date 2026/7/22 09:18
 */
public interface ITztgService {

    void save(Tztg tztg);

    boolean delete(Tztg tztg);

    boolean sendEmail(String email);
}
