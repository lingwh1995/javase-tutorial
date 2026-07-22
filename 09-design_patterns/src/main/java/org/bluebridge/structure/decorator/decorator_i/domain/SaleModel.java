package org.bluebridge.structure.decorator.decorator_i.domain;

/**
 * 销售模型
 *
 * @author lingwh
 * @date 2019/8/7 9:42
 */
public class SaleModel {

    /**
     * 销售的商品
     */
    private String goods;

    /**
     * 销售的数量
     */
    private int saleNum;

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public int getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }

    @Override
    public String toString() {
        return "SaleModel{" +
                "goods='" + goods + '\'' +
                ", saleNum=" + saleNum +
                '}';
    }
}
