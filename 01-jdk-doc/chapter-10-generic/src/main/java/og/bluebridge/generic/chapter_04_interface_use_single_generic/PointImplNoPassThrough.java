package og.bluebridge.generic.chapter_04_interface_use_single_generic;

import lombok.Data;

/**
 * 单个泛型 - 泛型不透传（实现类直接写死具体类型，不再向外暴露泛型。）
 *
 * @author lingwh
 * @date 2026/7/8 18:39
 */
@Data
public class PointImplNoPassThrough implements IPoint<Integer> {

    /**
     * 表示X坐标
     */
    private Integer x;

    /**
     * 表示Y坐标
     */
    private Integer y;
}
