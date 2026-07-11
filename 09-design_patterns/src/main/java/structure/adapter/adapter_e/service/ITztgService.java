package structure.adapter.adapter_e.service;

import structure.adapter.adapter_e.domain.Tztg;

/**
 * 通知通告接口
 *
 * @author lingwh
 * @date
 */
public interface ITztgService {
    void save(Tztg tztg);

    boolean delete(Tztg tztg);

    boolean sendEmail(String email);
}
