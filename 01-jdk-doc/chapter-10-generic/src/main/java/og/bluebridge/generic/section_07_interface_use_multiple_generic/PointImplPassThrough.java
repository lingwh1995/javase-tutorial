package og.bluebridge.generic.section_07_interface_use_multiple_generic;

import lombok.Data;

/**
 * 多个泛型透传实现
 *
 * @author lingwh
 * @date 2019/3/10 10:30
 */
@Data
public class PointImplPassThrough<T, U> implements IPoint<T, U> {

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
}
