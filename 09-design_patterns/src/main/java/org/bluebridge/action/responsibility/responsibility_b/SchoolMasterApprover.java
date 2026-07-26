package org.bluebridge.action.responsibility.responsibility_b;

/**
 * 校长 处理金额大于 10000 的请求
 *
 * @author lingwh
 * @date 2026/7/22 15:03
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
