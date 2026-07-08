package og.bluebridge.generic.chapter_04_interface_use_single_generic;

import lombok.Data;

/**
 * 单个泛型 - 泛型透传（接口和实现类共用同一个泛型类型，实现类本身仍是泛型类，类型由外部调用方决定，这个行为就叫泛型透传。）
 *
 * 正确标准写法（推荐使用） -> public class PointImpl implements IPoint<Integer> {}
 * 错误 / 不规范写法（禁止使用） -> public class PointImpl<Integer> implements IPoint {}
 */
@Data
public class PointImplPassThrough<T> implements IPoint<T> {

    /**
     * 表示X坐标
     */
    private T x;

    /**
     * 表示Y坐标
     */
    private T y;

}
