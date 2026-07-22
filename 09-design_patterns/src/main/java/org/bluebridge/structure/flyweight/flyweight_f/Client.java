package org.bluebridge.structure.flyweight.flyweight_f;

/**
 * 客户端测试
 *
 * @author lingwh
 * @date 2019/8/1 17:11
 */
public class Client {

    public static void main(String[] args) {
        SecurityMgr mgr = SecurityMgr.getInstance();
        boolean f1 = mgr.hasAuthority("张三", "薪资数据", "查看");
        boolean f2 = mgr.hasAuthority("李四", "薪资数据", "查看");
        boolean f3 = mgr.hasAuthority("李四", "薪资数据", "修改");

        System.out.println("f1==" + f1);
        System.out.println("f2==" + f2);
        System.out.println("f3==" + f3);
        for (int i = 0; i < 3; i++) {
            mgr.hasAuthority("张三" + i, "薪资数据", "查看");
        }

        // 特别提醒：这里查看的引用次数，不是指测试使用的次数，指的是
        // SecurityMgr的queryByUser方法通过享元工厂去获取享元对象的次数
        System.out.println(
                "薪资数据,查看 被引用了" + FlyweightFactory.getInstance().getUseTimes("薪资数据,查看") + "次");
        System.out.println(
                "薪资数据,修改 被引用了" + FlyweightFactory.getInstance().getUseTimes("薪资数据,修改") + "次");
        System.out.println(
                "人员列表,查看 被引用了" + FlyweightFactory.getInstance().getUseTimes("人员列表,查看") + "次");
    }
}
