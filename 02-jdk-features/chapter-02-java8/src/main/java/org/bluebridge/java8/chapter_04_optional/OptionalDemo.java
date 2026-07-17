package org.bluebridge.java8.chapter_04_optional;

import org.junit.Test;

import java.util.Optional;

/**
 * Optional使用示例
 *
 * @author lingwh
 * @date 2026/6/22 15:10
 */
public class OptionalDemo {

    /**
     * V1.0 不判断boy和girl是否为空，可能会报空指针异常
     *
     * @param boy
     * @return
     */
    public String getGirlNameV1(Boy boy) {
        return boy.getGirl().getName();
    }

    /**
     * 测试 getGirlNameV1
     */
    @Test
    public void testGirlNameV1() {
        Boy boy = new Boy();
        String girlName = getGirlNameV1(boy);
        System.out.println(girlName);
    }

    /**
     * V2.0 使用传统方法判断boy和girl是否为空，可以避免空指针异常
     *
     * @param boy
     * @return
     */
    public String getGirlNameV2(Boy boy) {
        if (null != boy) {
            Girl girl = boy.getGirl();
            if (null != girl) {
                return girl.getName();
            }
        }
        return null;
    }

    /**
     * 测试 getGirlNameV2
     */
    @Test
    public void testGirlNameV2() {
        Boy boy = new Boy();
        String girlName = getGirlNameV2(boy);
        System.out.println(girlName);
    }

    /**
     * V3.0 使用Optional类处理空指针问题
     *
     * @param boy
     * @return
     */
    public String getGirlNameV3(Boy boy) {
        // boy = null;
        Optional<Boy> boyOptional = Optional.ofNullable(boy);
        // 此时的boy一定非空
        boy = boyOptional.orElse(new Boy(new Girl("boy为空时-古力娜扎")));
        Girl girl = boy.getGirl();
        Optional<Girl> girlOptional = Optional.ofNullable(girl);
        // 此时的girl一定非空
        girl = girlOptional.orElse(new Girl("girl为空时-范冰冰"));
        return girl.getName();
    }

    /**
     * 测试 getGirlNameV2
     */
    @Test
    public void testGirlNameV3() {
        Boy boy = new Boy();
        String girlName = getGirlNameV3(boy);
        System.out.println(girlName);
    }
}
