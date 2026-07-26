package org.bluebridge.structure.composite.composite_c;

import java.util.ArrayList;
import java.util.List;

/**
 * 大学
 *
 * @author lingwh
 * @date 2026/7/22 11:52
 */
public class University extends OrganizationComponment {

    /**
     * 存放的 College
     */
    List<OrganizationComponment> collegeList = new ArrayList<OrganizationComponment>();

    public University(String name, String desc) {
        super(name, desc);
    }

    @Override
    protected void add(OrganizationComponment organizationComponment) {
        collegeList.add(organizationComponment);
    }

    @Override
    protected void remove(OrganizationComponment organizationComponment) {
        collegeList.remove(organizationComponment);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getDesc() {
        return super.getDesc();
    }

    /**
     * 输出 University 中所包含的学院
     */
    @Override
    public void print() {
        System.out.println("-------------" + getName() + ":" + getDesc() + "-------------");
        // 遍历
        for (OrganizationComponment collge : collegeList) {
            collge.print();
        }
    }
}
