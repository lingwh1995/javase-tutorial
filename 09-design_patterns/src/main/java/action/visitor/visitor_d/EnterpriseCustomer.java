package action.visitor.visitor_d;

/**
 * 企业客户
 *
 * @author lingwh
 * @date 2019/10/11 9:35
 */
public class EnterpriseCustomer extends Customer {

    private String linkman;
    private String linkTelephone;
    private String registerAddress;

    /**
     * 接收访问者的访问
     *
     * @param visitor 访问者对象
     */
    @Override
    void accept(Visitor visitor) {
        // 回调访问者对象的相应方法
        visitor.visitEnterpriseCustomer(this);
    }
}
