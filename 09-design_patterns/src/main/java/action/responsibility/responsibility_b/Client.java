package action.responsibility.responsibility_b;

/**
 * 客户端测试
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Client {

    public static void main(String[] args) {
        // 创建请求
        PurchaseRequest purchaseRequest = new PurchaseRequest(1, 15100, 1);

        // 创建相关的审批人
        // 系主任
        Approver departmentApprover = new DepartmentApprover("系主任");
        // 学院主任
        Approver collegeApprover = new CollegeApprover("学院主任");
        // 校长
        Approver schoolMasterApprover = new SchoolMasterApprover("校长");

        // 将各个审批级别的下一个审批者设置好
        departmentApprover.setApprover(collegeApprover);
        collegeApprover.setApprover(schoolMasterApprover);
        schoolMasterApprover.setApprover(departmentApprover);

        departmentApprover.processeRequest(purchaseRequest);
    }
}
