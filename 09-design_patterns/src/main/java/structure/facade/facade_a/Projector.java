package structure.facade.facade_a;

/**
 * 投影仪，使用饿汉式
 *
 * @author lingwh
 * @date 2019/3/19 00:00
 */
public class Projector {
    private static Projector projector = new Projector();

    public static Projector getInstance() {
        return projector;
    }

    /**
     * 开启投影仪
     */
    public void on() {
        System.out.println("projector on......");
    }

    /**
     * 关闭投影仪
     */
    public void off() {
        System.out.println("projector off......");
    }
}
