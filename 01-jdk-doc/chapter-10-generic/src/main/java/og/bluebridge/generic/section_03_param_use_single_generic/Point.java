package og.bluebridge.generic.section_03_param_use_single_generic;

/**
 * 单个泛型
 *
 * @author lingwh
 * @date 2019/3/10 10:30
 */
public class Point<T> {

    /**
     * 坐标点描述
     */
    private T desc;

    public T getDesc() {
        return desc;
    }

    /**
     * 在方法参数中使用泛型
     *
     * @param desc
     */
    public void setDesc(T desc) {
        this.desc = desc;
    }
}
