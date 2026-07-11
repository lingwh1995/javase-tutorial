package structure.facade.facade_a;

/**
 * @author lingwh
 * @desc 电影幕布
 * @date 2026/7/9 00:00
 */
public class Screen {
    private static Screen screen = new Screen();

    public static Screen getInstance() {
        return screen;
    }

    /**
     * 屏幕上升
     */
    public void up() {
        System.out.println("screen up......");
    }

    /**
     * 屏幕下降
     */
    public void down() {
        System.out.println("screen down......");
    }

    /**
     * 屏幕灯光打开
     */
    public void light() {
        System.out.println("light......");
    }
}
