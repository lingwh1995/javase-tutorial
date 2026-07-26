package org.bluebridge.structure.flyweight.flyweight_a;

/**
 * 具体网站实现
 *
 * @author lingwh
 * @date 2026/7/22 08:42
 */
public class ConcreteWebSite implements WebSite {

    /**
     * 网站发布的形式 共享的内部状态
     */
    private String type = "";

    public ConcreteWebSite(String type) {
        this.type = type;
    }

    @Override
    public void use(User user) {
        System.out.println("网站发布的形式为:" + type + ",使用者是:" + user.getName());
    }
}
