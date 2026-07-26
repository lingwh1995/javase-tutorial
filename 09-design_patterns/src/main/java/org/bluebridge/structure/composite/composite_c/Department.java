package org.bluebridge.structure.composite.composite_c;

/**
 * 系
 *
 * @author lingwh
 * @date 2026/7/22 10:35
 */
public class Department extends OrganizationComponment {

    public Department(String name, String desc) {
        super(name, desc);
    }

    // 注意：add() 和 remove() 方法就不需要再重写了，因为 Department 是叶子节点，不用去管理子节点了

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getDesc() {
        return super.getDesc();
    }

    /**
     * 输出 College 中所包含的学院
     */
    @Override
    public void print() {
        System.out.println(getName() + ":" + getDesc());
    }
}
