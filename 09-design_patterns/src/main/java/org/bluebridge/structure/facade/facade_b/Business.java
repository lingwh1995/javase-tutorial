package org.bluebridge.structure.facade.facade_b;

/**
 * 示意生成逻辑层的模块
 *
 * @author lingwh
 * @date 2019/9/12 8:54
 */
public class Business {

    public void generate() {
        ConfigModel cm = ConfigManager.getInstance().getConfigData();
        if (cm.isNeedGenBusiness()) {
            System.out.println("正在生成逻辑层代码文件");
        }
    }
}
