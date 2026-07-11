package action.responsibility.responsibility_b;

/**
 * @author lingwh
 * @desc 校长 处理金额大于10000的请求
 * @date 2026/7/9 00:00
 */
public class SchoolMasterApprover extends Approver {
    public SchoolMasterApprover(String name) {
        super(name);
    }

    @Override
    public void processeRequest(PurchaseRequest purchaseRequest) {
        System.out.println("校长开始处理请求~~");
        if (10000 < purchaseRequest.getPrice()) {
            System.out.println("请求编号为" + purchaseRequest.getId() + "的请求被" + this.name + "处理了......");
        } else {
            approver.processeRequest(purchaseRequest);
        }
    }
}
