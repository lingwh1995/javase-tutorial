package org.bluebridge.section_01_shallowclone;

import org.junit.Test;

/**
 * 浅克隆测试
 *
 * @author lingwh
 * @date 2026/7/8 18:39
 */
public class ShallowCloneTest {

    /**
     * 测试浅克隆
     */
    @Test
    public void testShallowClone() throws Exception {
        // 原型羊多利
        Sheep sheep = new Sheep();
        sheep.setAge(8);
        sheep.setName("多利");
        sheep.setSmallShellp(new String[] { "小多利1" });
        System.out.println("克隆前:" + sheep);
        // 克隆羊多利
        Sheep cloneSheep = sheep.clone();
        System.out.println(cloneSheep);
        System.out.println(sheep == cloneSheep);
        // 修改克隆对象的值
        // 修改基本类型数据的值:不会影响原型中该字段的值
        cloneSheep.setAge(89);
        // 修改String类型数据的值:不会影响原型中该字段的值
        cloneSheep.setName("你好");
        // 修改引用类型数据的值:会影响原型中该字段的值
        String[] sons = cloneSheep.getSmallShellp();
        sons[0] = "克隆小多利1";
        System.out.println("修改引用类型数据后的克隆对象:" + cloneSheep);
        System.out.println("修改引用类型数据后的原对象:" + sheep);
    }
}
