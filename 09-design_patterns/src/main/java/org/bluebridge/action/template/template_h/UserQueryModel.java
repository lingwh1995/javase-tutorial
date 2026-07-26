package org.bluebridge.action.template.template_h;

/**
 * 用户查询数据模型
 *
 * @author lingwh
 * @date 2019/8/26 18:26
 */
public class UserQueryModel extends UserModel {

    /**
     * 年龄是一个区间查询，也就是年龄查询的条件可以是：
     * age >= 条件值 1 and age <= 条件值 2
     * 把 UserModel 中的 age 当作条件值 1，这里定义的 age2 当作条件值 2
     */
    private int age2;

    public int getAge2() {
        return age2;
    }

    public void setAge2(int age2) {
        this.age2 = age2;
    }
}
