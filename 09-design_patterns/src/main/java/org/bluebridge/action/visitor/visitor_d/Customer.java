package org.bluebridge.action.visitor.visitor_d;

/**
 * 客户
 *
 * @author lingwh
 * @since 2019/10/11 9:34
 */
public abstract class Customer {

    private String customerId;
    private String name;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 接收访问者的访问
     *
     * @param visitor 访问者对象
     */
    abstract void accept(Visitor visitor);
}
