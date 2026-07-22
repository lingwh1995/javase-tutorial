package org.bluebridge.structure.flyweight.flyweight_d;

/**
 * 享元接口
 *
 * @author lingwh
 * @date 2019/7/30 13:19
 */
public interface Flyweight {

    /**
     * 判断传入的安全实体和内部权限，是否和享元对象的内部状态相匹配
     *
     * @param securityEntity 安全实体:被权限系统检测的对象
     * @param authority 具体的权限
     * @return
     */
    boolean match(String securityEntity, String authority);
}
