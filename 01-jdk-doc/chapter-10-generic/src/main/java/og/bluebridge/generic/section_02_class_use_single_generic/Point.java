package og.bluebridge.generic.section_02_class_use_single_generic;

import lombok.Data;

/**
 * 单个泛型
 *
 * @author lingwh
 * @date 2019/3/10 10:30
 */
@Data
public class Point<T> {

    /**
     * 表示 X 坐标
     */
    private T x;

    /**
     * 表示 Y 坐标
     */
    private T y;
}
