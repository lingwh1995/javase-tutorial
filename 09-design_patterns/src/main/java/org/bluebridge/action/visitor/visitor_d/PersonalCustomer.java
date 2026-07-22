package org.bluebridge.action.visitor.visitor_d;

/**
 * 个人客户
 *
 * @author lingwh
 * @date 2019/10/11 9:37
 */
public class PersonalCustomer extends Customer {

    private String telephone;
    private int age;

    @Override
    public void accept(Visitor visitor) {
        // 回调访问者对象的相应方法
        visitor.visitPersonalCustomer(this);
    }
}
