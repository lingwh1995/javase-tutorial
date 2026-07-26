package org.bluebridge.action.responsibility.responsibility_b;

/**
 * 学院主任 处理大于等于 5000 小于等于 10000 的采购请求
 *
 * @author lingwh
 * @date 2026/7/22 15:03
 */
public class CollegeApprover extends Approver {

    public CollegeApprover(String name) {
        super(name);
    }

    @Override
    public void processeRequest(PurchaseRequest purchaseRequest) {
        System.out.println("院长开始处理请求~~");
        if (5000 <= purchaseRequest.getPrice() && purchaseRequest.getPrice() <= 10000) {
            System.out.println("请求编号为" + purchaseRequest.getId() + "的请求被" + this.name + "处理了......");
        } else {
            approver.processeRequest(purchaseRequest);
        }
    }
}
