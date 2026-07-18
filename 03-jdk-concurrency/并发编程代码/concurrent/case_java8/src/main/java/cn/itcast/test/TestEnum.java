package cn.itcast.test;

/**
 * 枚举测试
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class TestEnum {

    public static void main(String[] args) {
        System.out.println(Color.ONE);
    }
}

enum Color{

    ONE;
    Color() {
        System.out.println("init");
    }
    static {
        System.out.println("static");
    }
}
