package og.bluebridge.generic.section_04_param_use_multiple_generic;

/**
 * 多个泛型
 *
 * @author lingwh
 * @date 2026/7/8 18:39
 */
public class Point<T, U> {

    /**
     * 表示 X 坐标
     */
    private T x;

    /**
     * 表示 Y 坐标
     */
    private T y;

    /**
     * 坐标点描述
     */
    private U desc;

    public Point(T x, T y, U desc) {
        this.x = x;
        this.y = y;
        this.desc = desc;
    }
}
