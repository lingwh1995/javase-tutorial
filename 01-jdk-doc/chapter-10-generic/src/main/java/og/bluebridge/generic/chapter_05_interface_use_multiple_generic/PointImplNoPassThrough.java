package og.bluebridge.generic.chapter_05_interface_use_multiple_generic;

import lombok.Data;

/**
 * 多个泛型（实现类这里T、U不用换，这是最标准的写法） - 泛型不透传（实现类直接写死具体类型，不再向外暴露泛型。）
 *
 * @author lingwh
 * @date 2026/7/8 18:39
 */
@Data
public class PointImplNoPassThrough implements IPoint<Integer, String> {

    /**
     * 表示X坐标
     */
    private Integer x;

    /**
     * 表示Y坐标
     */
    private Integer y;

    /**
     * 坐标点描述
     */
    private String desc;
}
