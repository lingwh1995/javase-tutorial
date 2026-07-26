package org.bluebridge.structure.flyweight.flyweight_f;

/**
 * 享元接口
 *
 * @author lingwh
 * @date 2026/7/22 10:05
 */
public interface Flyweight {

    /**
     * 判断传入的安全实体和内部权限，是否和享元对象的内部状态相匹配
     *
     * @param securityEntity 安全实体：被权限系统检测的对象
     * @param authority 具体的权限
     * @return
     */
    boolean match(String securityEntity, String authority);

    /**
     * 为 flyweight 添加子 flyweight 对象
     *
     * @param flyweight 被添加的子 flyweight 对象
     */
    void add(Flyweight flyweight);
}
