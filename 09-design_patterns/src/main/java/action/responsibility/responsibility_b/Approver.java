package action.responsibility.responsibility_b;

/**
 * @author lingwh
 * @desc 审批人抽象类
 * @date 2026/7/9 00:00
 */
public abstract class Approver {

    /**
     * 下一个处理者
     */
    protected Approver approver;

    /**
     * 名字
     */
    protected String name;

    public Approver(String name) {
        this.name = name;
    }

    /**
     * 设置下一个处理者
     *
     * @param approver
     */
    public void setApprover(Approver approver) {
        this.approver = approver;
    }

    /**
     * 处理请求的方法
     */
    public abstract void processeRequest(PurchaseRequest purchaseRequest);
}
