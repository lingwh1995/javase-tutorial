package og.bluebridge.generic.chapter_03_class_use_multiple_generic;

import lombok.Data;

/**
 * @author lingwh
 * @desc 多个泛型
 * @date 2026/7/9 00:00
 */
@Data
public class Point<T, U> {

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
