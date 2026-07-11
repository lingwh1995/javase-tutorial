package action.visitor.visitor_d;

/**
 * 访问者接口
 *
 * @author lingwh
 * @date 2019/10/11 9:38
 */
public interface Visitor {

    /**
     * 访问企业客户，相当于给企业客户添加访问者的功能
     *
     * @param ec 企业客户的对象
     */
    void visitEnterpriseCustomer(EnterpriseCustomer ec);

    /**
     * 访问个人客户，相当于给个人客户添加访问者的功能
     *
     * @param pc 个人客户的对象
     */
    void visitPersonalCustomer(PersonalCustomer pc);
}
