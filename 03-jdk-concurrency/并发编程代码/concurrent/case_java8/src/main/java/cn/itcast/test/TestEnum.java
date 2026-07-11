package cn.itcast.test;

/**
 * @author lingwh
 * @desc 枚举测试
 * @date 2026/7/9 00:00
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
