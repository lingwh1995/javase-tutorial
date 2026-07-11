package structure.facade.facade_a;

/**
 * 爆米花机，使用饿汉式
 *
 * @author lingwh
 * @date 2019/3/16 00:00
 */
public class Propcorn {
    private static Propcorn propcorn = new Propcorn();

    public static Propcorn getInstance() {
        return propcorn;
    }

    public void on() {
        System.out.println("propcorn on......");
    }

    public void off() {
        System.out.println("propcorn off......");
    }

    /**
     * 正在生产爆米花
     */
    public void pop() {
        System.out.println("producer propcorn......");
    }
}
