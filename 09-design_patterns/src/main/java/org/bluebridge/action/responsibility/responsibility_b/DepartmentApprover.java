package org.bluebridge.action.responsibility.responsibility_b;

/**
 * 系主任 处理金额小于等于 5000 的采购请求
 *
 * @author lingwh
 * @date 2026/7/22 15:03
 */
public class DepartmentApprover extends Approver {

    public DepartmentApprover(String name) {
        super(name);
    }

    @Override
    public void processeRequest(PurchaseRequest purchaseRequest) {
        System.out.println("系主任开始处理请求~~");
        if (purchaseRequest.getPrice() < 5000) {
            System.out.println("请求编号为" + purchaseRequest.getId() + "的请求被" + this.name + "处理了......");
        } else {
            approver.processeRequest(purchaseRequest);
        }
    }
}
