package org.bluebridge.structure.flyweight.flyweight_e;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂
 *
 * @author lingwh
 * @date 2019/8/1 13:53
 */
public class FlyweightFactory {

    private static FlyweightFactory factory = new FlyweightFactory();

    private FlyweightFactory() {}

    public static FlyweightFactory getInstance() {
        return factory;
    }

    /**
     * 缓存多个flyweight对象
     */
    private Map<String, Flyweight> fsMap = new HashMap<String, Flyweight>();

    /**
     * 获取key对应的享元对象
     *
     * @param key 获取享元对象的key
     * @return 对应的享元对象
     */
    public Flyweight getFlyweight(String key) {
        Flyweight flyweight = fsMap.get(key);
        if (flyweight == null) {
            flyweight = new AuthorizationFlyweight(key);
            fsMap.put(key, flyweight);
        }
        return flyweight;
    }
}
