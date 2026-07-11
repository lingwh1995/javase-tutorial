package org.bluebridge.lang3;

import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.RandomStringGenerator;
import org.junit.Test;

/**
 * @author lingwh
 * @desc RandomStringGenerator工具类测试
 * @date 2025/9/15 11:08
 */
@Slf4j
public class RandomStringGeneratorTest {

    /**
     * RandomStringGenerator hello world 案例
     */
    @Test
    public void testRandomStringGeneratorHelloWorld() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('a', 'z')  // ASCII 范围为 a-z
                .build();
        IntStream.range(0, 10).forEach(i -> {
            String generate = generator.generate(10);
            log.info("generate-1: {}", generate);
        });
    }

    /**
     * RandomStringGenerator 筛选符合条件的字符生成随机字符串案例
     */
    @Test
    public void testRandomStringGeneratorFilteredByCondition() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')                  // ASCII 范围为 0-9, A-Z, a-z
                //.filteredBy(LETTERS, DIGITS)          // 限制生成的字符串由字母和数组组成
                .filteredBy(Character::isLetterOrDigit) // 限制生成的字符串由字母和数组组成
                .build();
        IntStream.range(0, 10).forEach(i -> {
            String generate = generator.generate(10);
            log.info("generate-2: {}", generate);
        });
    }

    /**
     * RandomStringGenerator 筛选符合条件的字符生成随机字符串案例并且要求字符串不重复
     */
    @Test
    public void testRandomStringGeneratorFilteredByConditionNoRepeat() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')                  // ASCII 范围为 0-9, A-Z, a-z
                //.filteredBy(LETTERS, DIGITS)          // 限制生成的字符串由字母和数组组成
                .filteredBy(Character::isLetterOrDigit) // 限制生成的字符串由字母和数组组成
                .build();
        IntStream.range(0, 10).forEach(i -> {
            int targetLength = 10;
            String baseString = generator.generate(targetLength * 2);
            // 移除重复元素
            String uniqueString = baseString.chars()
                    .distinct()
                    .limit(targetLength) // 确定最大长度
                    .collect(StringBuilder::new,
                            StringBuilder::appendCodePoint,
                            StringBuilder::append)
                    .toString();
            log.info("generate-3: {}", uniqueString);
        });
    }

    /**
     * RandomStringGenerator 从字符数组中选择字符生成随机字符串案例
     */
    @Test
    public void testRandomStringGeneratorSelectFrom() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .selectFrom("ABC123!@#!$%^&*".toCharArray())  // ASCII 范围为 ABC123!@# 这几个字符
                //.filteredBy(LETTERS, DIGITS)          // 限制生成的字符串由字母和数组组成
                .filteredBy(Character::isLetterOrDigit) // 限制生成的字符串由字母和数组组成
                .build();
        IntStream.range(0, 10).forEach(i -> {
            String generate = generator.generate(10);
            log.info("generate-4: {}", generate);
        });
    }

    /**
     * RandomStringGenerator 生成随机汉字案例
     */
    @Test
    public void testRandomStringGeneratorRandomChineseCharacter() {
        // 姓氏生成器
        RandomStringGenerator firstNameGenerator = new RandomStringGenerator.Builder()
                .selectFrom("赵钱孙李周吴郑王".toCharArray())
                .build();
        // 名字生成器
        RandomStringGenerator lastNameGenerator = new RandomStringGenerator.Builder()
                .selectFrom("伟杰勇飞晨思华瑞英忠信乐".toCharArray())
                .build();
        IntStream.range(0, 10).forEach(i -> {
            String firstName = firstNameGenerator.generate(1);
            String lastName = lastNameGenerator.generate(1);
            String fullName = firstName + lastName;
            log.info("generate-5: {}", fullName);
        });
    }

    /**
     * RandomStringGenerator 生成随机人名案例
     */
    @Test
    public void testRandomStringGeneratorRandomName() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('\u4e00', '\u9fa5')
                .build();
        IntStream.range(0, 10).forEach(i -> {
            String chineseCharacter = generator.generate(3);
            log.info("generate-6: {}", chineseCharacter);
        });
    }

    /**
     * RandomStringGenerator 生成手机号码案例一
     */
    @Test
    public void testRandomStringGeneratorPhoneNumber1() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('1', 'z')
                .filteredBy(Character::isDigit) // 限制生成的字符串由纯数组组成
                .build();
        IntStream.range(0, 10).forEach(i -> {
            String phoneNumber = generator.generate(10);
            phoneNumber = "1" + phoneNumber;
            log.info("generate-7: {}", phoneNumber);
        });
    }

    /**
     * RandomStringGenerator 生成手机号码案例二
     */
    @Test
    public void testRandomStringGeneratorPhoneNumber2() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('1', '9')
                .build();
        IntStream.range(0, 10).forEach(i -> {
            String phoneNumber = generator.generate(10);
            phoneNumber = "1" + phoneNumber;
            log.info("generate-8: {}", phoneNumber);
        });
    }
}
