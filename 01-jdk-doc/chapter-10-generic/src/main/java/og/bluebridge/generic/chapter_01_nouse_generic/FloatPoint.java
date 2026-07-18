package og.bluebridge.generic.chapter_01_nouse_generic;

import lombok.Data;
import lombok.ToString;

/**
 * 设置Float类型的点坐标
 *
 * @author lingwh
 * @date 2026/7/8 18:39
 */
@Data
@ToString
public class FloatPoint {

    /**
     * 表示 X 坐标
     */
    private Float x;

    /**
     * 表示 Y 坐标
     */
    private Float y;
}
