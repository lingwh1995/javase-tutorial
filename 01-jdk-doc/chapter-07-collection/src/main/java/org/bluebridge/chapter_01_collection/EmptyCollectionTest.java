package org.bluebridge.chapter_01_collection;


import org.junit.Test;

import java.util.*;

/**
 * @author ronin
 * @version V1.0
 * @desc 避免抛出空指针异常的
 * @since 2019/7/9 15:29
 */
public class EmptyCollectionTest {

    /**
     * 测试获取空的List集合
     *      具体使用哪个方式，要看返回结果是否需要泛型支持
     */
    @Test
    public void testEmptyList(){
        //没有泛型支持
        List emptyListWithoutGenericSupport = Collections.EMPTY_LIST;
        //有泛型支持
        List<String> stringEmptyListWithGenericSupport = Collections.emptyList();
    }

    /**
     * 测试获取空的Set集合
     *      体使用哪个方式，要看返回结果是否需要泛型支持
     */
    @Test
    public void testEmptySet(){
        //没有泛型支持
        Set emptySetWithoutGenericSupport = Collections.EMPTY_SET;
        //有泛型支持
        Set<String> stringEmptySetWithGenericSupport = Collections.emptySet();
    }
    /**
     * 测试获取空的List集合
     */
    @Test
    public void testEmptyMap(){
        //没有泛型支持
        Map emptyMapWithoutGenericSupport = Collections.EMPTY_MAP;
        //有泛型支持
        Map<String, Object> emptyMapWithGenericSupport = Collections.emptyMap();
    }
}
