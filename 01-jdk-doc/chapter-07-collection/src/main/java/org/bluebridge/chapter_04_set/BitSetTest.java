package org.bluebridge.chapter_04_set;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * TODO BiSet属于数据结构范围知识 经典应用场景：快速判断所有员工是否打卡了
 *
 * @author lingwh
 * @date 2026/7/8 18:39
 */
@Slf4j
public class BitSetTest {

    /**
     * BitSet HelloWorld案例
     */
    @Test
    public void testBitSetHelloWorld() {
        // 创建一个具有10000位的bitset 初始所有位的值为false
        BitSet bitSet = new BitSet(10000);
        // 将指定位的值设为true
        bitSet.set(9999);
        // 或者bitSet.set(9999,true);
        // 输出指定位的值
        log.debug("第9999个元素： {}", bitSet.get(9999));
        log.debug("第9998个元素： {}", bitSet.get(9998));
    }

    /**
     * 使用BitSet统计重复一亿个电话号码中的重复号码
     */
    @Test
    public void testStatisticsDuplicateNumbers() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('1', '9')
                .build();
        IntStream.range(0, 10000).forEach(i -> {
            String phoneNumber = generator.generate(10);
            phoneNumber = "1" + phoneNumber;
            log.info("generate: {}", phoneNumber);
        });
    }

    /**
     * 使用BitSet统计生成随机数的个数
     */
    @Test
    public void testBitSetCountRandom() {
        // 创建list
        List<Integer> randomList = new ArrayList<>();
        // 给list中放入100个随机数
        IntStream.range(0, 100).forEach(i -> randomList.add(RandomUtils.nextInt(0, 100)));
        StringBuilder randomBuilder = new StringBuilder();
        randomList.stream().forEach(random -> randomBuilder.append(random).append(" "));
        log.debug("0~100之间生成的随机数有: {}", randomBuilder);
        log.debug("0~100之间生成的随机数有: {} 个", randomList.size());
        log.debug("---------------------------------");

        // 创建bitset
        BitSet bitSet = new BitSet(100);
        // 把随机数放入到bitset中
        randomList.stream().forEach(random -> bitSet.set(random));
        // int cardinality() 返回此BitSet中比特设置为true的数目，就是BitSet中存放的有效位的个数，如果有重复运算会进行自动去重
        log.debug("0~100存在BitSet的随机数有: {} 个", bitSet.cardinality());
        StringBuilder bitSetRandomBuilder = new StringBuilder();
        bitSet.stream().forEach(random -> bitSetRandomBuilder.append(random).append(" "));
        log.debug("BitSet中存放的随机数：{}", bitSetRandomBuilder);
        log.debug("---------------------------------");

        AtomicInteger count = new AtomicInteger();
        StringBuilder notInBitSetRandomBuilder = new StringBuilder();
        IntStream.range(0, 100)
                .forEach(i -> {
                    if (!bitSet.get(i)) {
                        notInBitSetRandomBuilder.append(i).append(" ");
                        count.getAndIncrement();
                    }
                });
        log.debug("0~100不在上述随机数中有: {}", notInBitSetRandomBuilder);
        log.debug("0~100不在上述随机数中有: {} 个", count.get());
        log.debug("---------------------------------");
    }
}
