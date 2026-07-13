package structure.composite.composite_c;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Client {

    public static void main(String[] args) {
        // 创建大学
        OrganizationComponment university = new University("ufe", "西安财经大学");

        // 创建学院
        OrganizationComponment xxCollege = new College("xxxy", "信息学院");
        OrganizationComponment jrCollege = new College("jrxy", "金融学院");

        // 创建学院下面的各个系(专业)
        // 信息学院
        xxCollege.add(new Department("ec", "电子商务"));
        xxCollege.add(new Department("software", "软件工程"));
        // 金融学院
        jrCollege.add(new Department("kj", "会计学院"));
        jrCollege.add(new Department("tj", "统计学院"));

        // 将学院加入到学校
        university.add(xxCollege);
        university.add(jrCollege);
        // 打印University所有下属机构信息
        university.print();
        // 打印College所有下属机构信息
        // xxCollege.print();
    }
}
