package og.bluebridge.generic.chapter_02_class_use_single_generic;

import lombok.Data;

/**
 * @author lingwh
 * @desc 单个泛型
 * @date 2026/7/9 00:00
 */
@Data
public class Point<T> {

    /**
     * 表示X坐标
     */
    private T x;

    /**
     * 表示Y坐标
     */
    private T y;
}
