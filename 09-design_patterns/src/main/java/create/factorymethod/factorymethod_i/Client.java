package create.factorymethod.factorymethod_i;

import org.junit.Test;

/**
 * @author lingwh
 * @desc 客户端测试
 * @date 2026/7/9 00:00
 */
public class Client {

    @Test
    public void fun() {
        // Adidas商店
        ClothesStore adidasClothesStore = new AdidasClothesStore();
        adidasClothesStore.pack();
        System.out.println("----------------");

        // Nike商店
        ClothesStore nikeClothesStore = new NikeClothesStore();
        nikeClothesStore.pack();

        // Puma商店
        System.out.println("----------------");
        ClothesStore pumaClothesStore = new PumaClothesStore();
        pumaClothesStore.pack();
    }
}
