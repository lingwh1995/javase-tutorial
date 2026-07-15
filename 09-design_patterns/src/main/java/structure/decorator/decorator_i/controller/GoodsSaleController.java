package structure.decorator.decorator_i.controller;

import structure.decorator.decorator_i.decorator.AuthorityDecorator;
import structure.decorator.decorator_i.decorator.LogDecorator;
import structure.decorator.decorator_i.domain.SaleModel;
import structure.decorator.decorator_i.service.GoodsSaleService;
import structure.decorator.decorator_i.service.IGoodsSaleService;

/**
 * 商品销售控制器
 *
 * @author lingwh
 * @date 2019/8/7 9:53
 */
public class GoodsSaleController {

    public String sale() {
        // 得到业务接口，组合装饰器
        IGoodsSaleService goodsSaleService = new GoodsSaleService();
        goodsSaleService = new LogDecorator(goodsSaleService);
        goodsSaleService = new AuthorityDecorator(goodsSaleService);

        // 准备测试数据
        SaleModel saleModel = new SaleModel();
        saleModel.setSaleNum(29);
        saleModel.setGoods("冰箱");

        // 调用业务功能
        goodsSaleService.sale("张三", "张三丰", saleModel);
        goodsSaleService.sale("李四", "张三丰", saleModel);
        return null;
    }
}
