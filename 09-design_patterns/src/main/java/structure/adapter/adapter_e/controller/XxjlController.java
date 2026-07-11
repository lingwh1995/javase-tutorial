package structure.adapter.adapter_e.controller;

import structure.adapter.adapter_e.domain.Xxjl;
import structure.adapter.adapter_e.service.IXxjlService;
import structure.adapter.adapter_e.service.XxjlAdapterService;
import structure.adapter.adapter_e.service.XxjlSerivice;

/**
 * 需求: 保存信息交流的同时，发送邮件并且短信提醒
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class XxjlController {
    // 多态
    private IXxjlService xxjlService = new XxjlSerivice();
    private IXxjlService xxjlAdapterService = new XxjlAdapterService();

    public String save(Xxjl xxjl) {
        xxjlAdapterService.save(xxjl);
        return null;
    }

    public String delete(Xxjl xxjl) {
        xxjlService.delete(xxjl);
        return null;
    }
}
