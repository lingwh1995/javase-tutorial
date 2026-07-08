package og.bluebridge.generic.chapter_01_nouse_generic;

import lombok.Data;
import lombok.ToString;

/**
 * 设置Float类型的点坐标
 */
@Data
@ToString
public  class FloatPoint{
    // 表示X坐标
    private Float x;
    // 表示Y坐标
    private Float y;
}