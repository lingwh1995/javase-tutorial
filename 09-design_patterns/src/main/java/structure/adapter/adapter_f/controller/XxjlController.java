package structure.adapter.adapter_f.controller;

import structure.adapter.adapter_f.domain.Xxjl;
import structure.adapter.adapter_f.service.IXxjlService;
import structure.adapter.adapter_f.service.XxjlSerivice;

/**
 * 需求: 保存信息交流的同时，发送邮件并且短信提醒
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class XxjlController {

    // 多态
    private IXxjlService xxjlService = new XxjlSerivice();

    // @RequestMappng("xxxx")
    public String save(Xxjl xxjl) {
        xxjlService.save(xxjl);
        return null;
    }

    // @RequestMappng("xxxx")
    public String delete(Xxjl xxjl) {
        xxjlService.delete(xxjl);
        return null;
    }
}
