package org.bluebridge.structure.adapter.adapter_g_builder.controller;

import org.bluebridge.structure.adapter.adapter_g_builder.domain.Xxjl;
import org.bluebridge.structure.adapter.adapter_g_builder.service.IXxjlService;
import org.bluebridge.structure.adapter.adapter_g_builder.service.XxjlSerivice;

/**
 * 需求: 保存信息交流的同时，发送邮件并且短信提醒
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class XxjlController {

    private IXxjlService xxjlService = new XxjlSerivice();

    // @RequestMappng("xxxx")
    public String save(Xxjl xxjl) {
        try {
            xxjlService.save(xxjl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // @RequestMappng("xxxx")
    public String delete(Xxjl xxjl) {
        try {
            xxjlService.delete(xxjl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
