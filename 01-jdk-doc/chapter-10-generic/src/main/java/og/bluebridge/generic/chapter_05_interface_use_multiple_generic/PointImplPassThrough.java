package og.bluebridge.generic.chapter_05_interface_use_multiple_generic;

import lombok.Data;

/**
 * 多个泛型透传实现
 *
 * @author lingwh
 * @date 2026/7/8 18:39
 */
@Data
public class PointImplPassThrough<T, U> implements IPoint<T, U> {

    /**
     * 表示X坐标
     */
    private T x;

    /**
     * 表示Y坐标
     */
    private T y;

    /**
     * 坐标点描述
     */
    private U desc;
}
