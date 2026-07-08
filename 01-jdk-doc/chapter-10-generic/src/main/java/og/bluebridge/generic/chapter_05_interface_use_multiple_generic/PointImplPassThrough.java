package og.bluebridge.generic.chapter_05_interface_use_multiple_generic;

import lombok.Data;

/**
 * 多个泛型（实现类这里T、U不用换，这是最标准的写法） - 泛型透传（接口和实现类共用同一个泛型类型，实现类本身仍是泛型类，类型由外部调用方决定，这个行为就叫泛型透传。）
 *
 * 正确标准写法（推荐使用） -> public class PointImpl implements IPoint<Integer, String> {}
 * 错误 / 不规范写法（禁止使用） -> public class PointImpl<Integer, String> implements IPoint {}
 */
@Data
public class PointImplPassThrough<T,U> implements IPoint<T, U> {

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
