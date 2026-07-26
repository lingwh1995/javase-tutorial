package org.bluebridge.create.factorymethod.factorymethod_i;

import org.junit.Test;

/**
 * 客户端测试
 *
 * @author lingwh
 * @date 2026/7/22 14:35
 */
public class Client {

    @Test
    public void fun() {
        // Adidas 商店
        ClothesStore adidasClothesStore = new AdidasClothesStore();
        adidasClothesStore.pack();
        System.out.println("----------------");

        // Nike 商店
        ClothesStore nikeClothesStore = new NikeClothesStore();
        nikeClothesStore.pack();

        // Puma 商店
        System.out.println("----------------");
        ClothesStore pumaClothesStore = new PumaClothesStore();
        pumaClothesStore.pack();
    }
}
