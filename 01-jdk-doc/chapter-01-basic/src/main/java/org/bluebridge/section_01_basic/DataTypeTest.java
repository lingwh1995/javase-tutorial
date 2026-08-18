package org.bluebridge.section_01_basic;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 *	Java 数据类型分为基本数据类型和引用数据类型
 *  1. 基本数据类型：包括 整数类型(byte、short、int、long)、浮点数类型(float、double)、字符类型(char)、布尔类型(boolean)。
 *  2. 引用数据类型：包括数组、 类、接口、枚举、注解、记录。
 *
 *	浮点类型详细说明
 *	1. 浮点类型的分类
 *	   - float：单精度，尾数可以精确到 7 位有效数字。很多情况下，精度很难满足需求。
 *	   - double：双精度，精度是 float 的两倍。通常采用此类型。
 * 	2. 浮点型常量有两种表示形式：
 *	   - 十进制数形式。如：5.12       512.0f        .512   (必须有小数点）
 *	   - 科学计数法形式。如：5.12e2      512E2     100E-2
 *	3. 浮点类型实际使用
 *	   - 定义 float 类型的变量，赋值时需要以"f"或"F"作为后缀。
 *	   - Java 的浮点型常量默认为 double 型。
 *	4. float 和 double 的区别
 *	   - float：float 是 Java 中的单精度浮点类型，它的范围和精度相对较低。它占用 32 位（4 字节）内存空间，并提供大约 6-7 位的有效数字
 *	   - double：double 是 Java 中的双精度浮点类型，它可以表示更大范围的数值并具有更高的精度。它占用 64 位（8 字节）内存空间，并提供大约 15 位的有效数字
 *	5. 浮点类型使用注意事项
 *	   - 浮点类型 float、double 的数据不适合在不容许舍入误差的金融计算领域。如果需要精确数字计算或保留指定位数的精度，需要使用 BigDecimal 类。
 *
 * @author lingwh
 * @date 2019/3/12 16:58
 */
@Slf4j
public class DataTypeTest {

    /**
     * 测试各种数类型的长度
     */
    @Test
    public void testDataTypeLength() {
        log.info("Byte类型数据所占字节长度:{}，Byte类型数据所占位数:{}", Byte.BYTES, Byte.SIZE);
        log.info("Short类型数据所占字节长度:{}，Short类型数据所占位数:{}", Short.BYTES, Short.SIZE);
        log.info("Integer类型数据所占字节长度:" + Integer.BYTES + ",Integer类型数据所占位数:"+Integer.SIZE);
        log.info("Long类型数据所占字节长度:{}，Long类型数据所占位数:{}", Long.BYTES, Long.SIZE);
        log.info("Double类型数据所占字节长度:{}，Double类型数据所占位数:{}", Double.BYTES, Double.SIZE);
        log.info("Float类型数据所占字节长度:{}，Float类型数据所占位数:{}", Float.BYTES, Float.SIZE);
        log.info("Character类型数据所占字节长度:{}，Character类型数据所占位数:{}", Character.BYTES, Character.SIZE);

        log.info("--------------------------------------------------------------------------------------------");
        log.info("Byte类型数据最小值:{}，Byte类型数据最大值:{}", Byte.MIN_VALUE, Byte.MAX_VALUE);
        log.info("Short类型数据最小值:{}，Short类型数据最大值:{}", Short.MIN_VALUE, Short.MAX_VALUE);
        log.info("Integer类型数据最小值:{}，Integer类型数据最大值:{}", Integer.MIN_VALUE, Integer.MAX_VALUE);
        log.info("Long类型数据最小值:{}，Long类型数据最大值:{}", Long.MIN_VALUE, Long.MAX_VALUE);
        log.info("Float类型数据最小值:{}，Float类型数据最大值:{}", Float.MIN_VALUE, Float.MAX_VALUE);
        log.info("Double类型数据最小值:{}，Double类型数据最大值:{}", Double.MIN_VALUE, Double.MAX_VALUE);
        log.info("Character类型数据最小值:{}，Character类型数据最大值:{}", Character.MIN_VALUE, Character.MAX_VALUE);
    }

    /**
     * 测试 float 和 double 类型数据精度
     *    通过测试可以看到浮点数在计算时有时会引入舍入丢失精度，所以在金融等领域要求精确计算时不能使用浮点类型来定义小数
     */
    @Test
    public void testFloatAndDoubleAccuracy() {
        // 测试  1
        float f1 = 0.1f;
        float f2 = 0.2f;
        float f3 = f1 + f2;
        log.info("f3 = {}", f3);

        // 测试 2
        float ff1 = 123123123f;
        float ff2 = ff1 + 1;
        log.info("ff1 = {}", ff1);
        log.info("ff2 = {}", ff2);
        log.info("ff1 == ff2: {}", ff1 == ff2);

        // 测试 3：可以看出 d1 + d2 并不等于 0.3
        // 在下面 shili3 中，我们期望将 d1 和 d2 两个 double 类型的变量相加得到 0.3。然而，由于浮点数的内部表示方式是基于二进制的，0.1 和 0.2 这
        // 两个十进制数在二进制中无法精确表示。因此，相加的结果并不是精确的 0.3，而是一个近似值 0.30000000000000004。这是由于在二进制表示
        // 中，无限循环小数 0.1 和 0.2 无法精确表示，而相加操作引入了舍入误差。所加相加并不能得到 0.3，只能得到一个近似 0.3 的值
        double d1 = 0.1;
        double d2 = 0.2;
        double d3 = d1 + d2;
        log.info("d3 = {}", d3);
    }
}
