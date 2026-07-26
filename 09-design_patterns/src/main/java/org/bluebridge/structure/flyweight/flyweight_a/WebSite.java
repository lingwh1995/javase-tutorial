package org.bluebridge.structure.flyweight.flyweight_a;

/**
 * 网站接口
 *
 * @author lingwh
 * @date 2026/7/22 08:25
 */
public interface WebSite {

    /**
     * @param user 外部状态
     */
    void use(User user);
}
