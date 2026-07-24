package og.bluebridge.generic.section_06_interface_use_single_generic;

import lombok.Data;

/**
 * 单个泛型 - 泛型不透传（实现类直接写死具体类型，不再向外暴露泛型。）
 *
 * @author lingwh
 * @date 2019/3/10 10:30
 */
@Data
public class PointImplNoPassThrough implements IPoint<Integer> {

    /**
     * 表示 X 坐标
     */
    private Integer x;

    /**
     * 表示 Y 坐标
     */
    private Integer y;
}
