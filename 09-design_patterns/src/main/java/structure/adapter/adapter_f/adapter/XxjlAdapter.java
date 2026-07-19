package structure.adapter.adapter_f.adapter;

import structure.adapter.adapter_f.dao.GatzlDao;
import structure.adapter.adapter_f.dao.TztgDao;
import structure.adapter.adapter_f.service.IGatzlService;
import structure.adapter.adapter_f.service.ITztgService;

/**
 * 信息交流适配器
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class XxjlAdapter extends AbstractXxjlAdapter {

    private GatzlDao gatzlDao = new GatzlDao();
    private TztgDao tztgDao = new TztgDao();

    @Override
    public boolean sendMsg(String phoneNumber) {
        IGatzlService iGatzlService = new AbstractXxjlAdapter() {
            @Override
            public boolean sendMsg(String phoneNumber) {
                return gatzlDao.sendMsg(phoneNumber);
            }
        };
        return iGatzlService.sendMsg(phoneNumber);
    }

    @Override
    public boolean sendEmail(String email) {
        ITztgService iTztgService = new AbstractXxjlAdapter() {
            @Override
            public boolean sendEmail(String email) {
                return tztgDao.sendEmail(email);
            }
        };
        return iTztgService.sendEmail(email);
    }
}
