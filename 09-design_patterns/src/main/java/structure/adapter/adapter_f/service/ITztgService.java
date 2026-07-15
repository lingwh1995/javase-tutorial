package structure.adapter.adapter_f.service;

import structure.adapter.adapter_f.domain.Tztg;

/**
 * 通知通告接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface ITztgService {

    void save(Tztg tztg);

    boolean delete(Tztg tztg);

    boolean sendEmail(String email);
}
